/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.amefurikozo.chat_app.client.R
import me.amefurikozo.chat_app.client.common.presentation.BaseViewModel
import me.amefurikozo.chat_app.client.feature.chat.domain.service.ChatService
import me.amefurikozo.chat_app.client.util.Resource
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
  app: Application,
  savedStateHandle: SavedStateHandle,
  private val chatService: ChatService,
) : BaseViewModel(app) {
  sealed class UiEvent {
    data class Toast(val text: String) : UiEvent()
  }

  private val _chat = mutableStateOf(ChatState())
  private val _text = mutableStateOf(String())
  private val _eventFlow = MutableSharedFlow<UiEvent>()
  val chat: State<ChatState> = _chat
  val text: State<String> = _text
  val eventFlow = _eventFlow.asSharedFlow()
  val username = savedStateHandle.get<String>("username")

  private fun getAllMessages() {
    viewModelScope.launch {
      _chat.value = chat.value.copy(isLoading = true)
      _chat.value = chat.value.copy(messages = chatService.getAll(), isLoading = false)
    }
  }

  private fun subscribe() {
    if (username != null) {
      viewModelScope.launch {
        when (val result = chatService.joinSession(username)) {
          is Resource.Success -> chatService
            .observeMessages()
            .onEach {
              _chat.value = chat.value.copy(messages = chat.value.messages.toMutableList().apply { add(0, it) })
            }
            .launchIn(viewModelScope)
          is Resource.Error -> _eventFlow.emit(
            UiEvent.Toast(result.message ?: stringResource(R.string.unknown_exception_message))
          )
        }
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    onEvent(ChatEvent.Disconnect)
  }

  fun onEvent(event: ChatEvent) {
    when (event) {
      is ChatEvent.InputChange -> _text.value = event.text
      is ChatEvent.Connect -> {
        getAllMessages()
        subscribe()
      }
      is ChatEvent.Disconnect -> viewModelScope.launch { chatService.leaveSession() }
      is ChatEvent.SendMessage -> if (text.value.isNotBlank()) viewModelScope.launch {
        chatService.sendMessage(text.value)
        _text.value = String()
      }
    }
  }

}
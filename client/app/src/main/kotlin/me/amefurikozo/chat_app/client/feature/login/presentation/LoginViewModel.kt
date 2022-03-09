/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.login.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.amefurikozo.chat_app.client.common.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
  sealed class UiEvent {
    data class Join(val username: String) : UiEvent()
  }

  private val _text = mutableStateOf(String())
  private val _eventFlow = MutableSharedFlow<UiEvent>()
  val text: State<String> = _text
  val eventFlow = _eventFlow.asSharedFlow()

  fun onEvent(event: LoginEvent) {
    when (event) {
      is LoginEvent.InputChange -> _text.value = event.text
      is LoginEvent.Join -> viewModelScope.launch { _eventFlow.emit(UiEvent.Join(text.value)) }
    }
  }
}
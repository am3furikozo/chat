/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.flow.collectLatest
import me.amefurikozo.chat_app.client.feature.chat.presentation.component.ChatInput
import me.amefurikozo.chat_app.client.feature.chat.presentation.component.ChatMessage

@Suppress("FunctionName")
@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
  val chat = viewModel.chat.value
  val context = LocalContext.current
  val lifecycleOwner = LocalLifecycleOwner.current

  LaunchedEffect(key1 = true) {
    viewModel.eventFlow.collectLatest {
      when (it) {
        is ChatViewModel.UiEvent.Toast -> Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
      }
    }
  }

  DisposableEffect(key1 = lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      when (event) {
        Lifecycle.Event.ON_START -> viewModel.onEvent(ChatEvent.Connect)
        Lifecycle.Event.ON_STOP -> viewModel.onEvent(ChatEvent.Disconnect)
        else -> Unit
      }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
  }

  Column(modifier = Modifier.fillMaxSize()) {
    LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth().padding(10.dp), reverseLayout = true) {
      items(chat.messages) { message ->
        Spacer(modifier = Modifier.height(32.dp))
        ChatMessage(
          modifier = Modifier.fillMaxWidth(),
          username = viewModel.username,
          message = message
        )
      }
    }
    ChatInput(
      modifier = Modifier.fillMaxWidth(),
      value = viewModel.text.value,
      onChange = { viewModel.onEvent(ChatEvent.InputChange(it)) },
      onClick = { viewModel.onEvent(ChatEvent.SendMessage) }
    )
  }
}
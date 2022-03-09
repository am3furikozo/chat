/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import me.amefurikozo.chat_app.client.R
import me.amefurikozo.chat_app.client.ui.theme.BabyBlue
import me.amefurikozo.chat_app.client.ui.theme.CustomDarkGray
import me.amefurikozo.chat_app.client.ui.theme.RedPink
import me.amefurikozo.chat_app.client.util.Screen

@Suppress("FunctionName")
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
  LaunchedEffect(key1 = true) {
    viewModel.eventFlow.collectLatest {
      when (it) {
        is LoginViewModel.UiEvent.Join -> navController.navigate(
          "${Screen.ChatScreen.route}?username=${it.username}"
        )
      }
    }
  }

  Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.End
    ) {
      TextField(
        modifier = Modifier.fillMaxWidth(),
        value = viewModel.text.value,
        colors = TextFieldDefaults.textFieldColors(
          backgroundColor = Color.BabyBlue,
          textColor = Color.CustomDarkGray,
          cursorColor = Color.RedPink,
          focusedIndicatorColor = Color.RedPink
        ),
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { viewModel.onEvent(LoginEvent.InputChange(it)) },
        placeholder = { Text(text = stringResource(R.string.login_screen_username_placeholder)) }
      )
      Spacer(modifier = Modifier.height(8.dp))
      Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.RedPink),
        onClick = { viewModel.onEvent(LoginEvent.Join) }) {
        Text(
          text = stringResource(R.string.login_screen_join_btn_text),
          color = Color.White,
          style = MaterialTheme.typography.button
        )
      }
    }
  }
}
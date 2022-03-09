/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import me.amefurikozo.chat_app.client.feature.chat.presentation.ChatScreen
import me.amefurikozo.chat_app.client.feature.login.presentation.LoginScreen
import me.amefurikozo.chat_app.client.ui.theme.ChatAppTheme
import me.amefurikozo.chat_app.client.util.Screen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ChatAppTheme {
        Surface(color = MaterialTheme.colors.background) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = Screen.UsernameScreen.route) {
            composable(route = Screen.UsernameScreen.route) {
              LoginScreen(navController = navController)
            }
            composable(
              route = "${Screen.ChatScreen.route}?username={username}",
              arguments = listOf(
                navArgument(name = "username") {
                  type = NavType.StringType
                  nullable = true
                }
              )
            ) {
              ChatScreen()
            }
          }
        }
      }
    }
  }
}
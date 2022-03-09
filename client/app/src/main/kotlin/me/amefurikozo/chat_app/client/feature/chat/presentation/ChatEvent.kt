/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation

sealed class ChatEvent {
  data class InputChange(val text: String) : ChatEvent()
  object Connect : ChatEvent()
  object Disconnect : ChatEvent()
  object SendMessage : ChatEvent()
}

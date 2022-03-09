/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation

import me.amefurikozo.chat_app.client.feature.chat.domain.model.Message

data class ChatState(
  val messages: List<Message> = emptyList(),
  val isLoading: Boolean = true
)

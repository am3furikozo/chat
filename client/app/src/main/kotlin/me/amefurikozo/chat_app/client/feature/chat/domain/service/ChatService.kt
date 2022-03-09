/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.domain.service

import kotlinx.coroutines.flow.Flow
import me.amefurikozo.chat_app.client.feature.chat.domain.model.Message
import me.amefurikozo.chat_app.client.util.Resource

interface ChatService {
  suspend fun getAll(): List<Message>

  suspend fun joinSession(username: String): Resource<Unit>

  suspend fun sendMessage(message: String)

  fun observeMessages(): Flow<Message>

  suspend fun leaveSession()
}
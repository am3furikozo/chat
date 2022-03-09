/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.feature.message.domain.repository

import me.amefurikozo.chat_app.server.feature.message.domain.model.Message

interface MessageRepository {
  suspend fun getAll(): List<Message>
  suspend fun save(message: Message)
  suspend fun getById(id: String): Message?
}

/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.feature.message.data.repository

import me.amefurikozo.chat_app.server.feature.message.data.source.MessageDao
import me.amefurikozo.chat_app.server.feature.message.domain.model.Message
import me.amefurikozo.chat_app.server.feature.message.domain.repository.MessageRepository

class MessageRepositoryImpl(private val messageDao: MessageDao) : MessageRepository {
  override suspend fun getAll(): List<Message> {
    return messageDao.getAll()
  }

  override suspend fun save(message: Message) {
    messageDao.insert(message)
  }

  override suspend fun getById(id: String): Message? {
    return messageDao.getById(id)
  }
}
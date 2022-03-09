/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.feature.message.data.source

import me.amefurikozo.chat_app.server.feature.message.domain.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDaoImpl(private val db: CoroutineDatabase) : MessageDao {
  private val messages = db.getCollection<Message>()

  override suspend fun getAll(): List<Message> {
    return messages.find().descendingSort(Message::timestamp).toList()
  }

  override suspend fun insert(message: Message) {
    messages.insertOne(message)
  }

  override suspend fun getById(id: String): Message? {
    return messages.findOneById(id)
  }
}
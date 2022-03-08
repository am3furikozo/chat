package me.amefurikozo.feature.message.data.source

import me.amefurikozo.feature.message.domain.model.Message

interface MessageDao {
  suspend fun getAll(): List<Message>
  suspend fun insert(message: Message)
  suspend fun getById(id: String): Message?
}
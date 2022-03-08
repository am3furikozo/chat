package me.amefurikozo.feature.message.domain.repository

import me.amefurikozo.feature.message.domain.model.Message

interface MessageRepository {
  suspend fun getAll(): List<Message>
  suspend fun save(message: Message)
  suspend fun getById(id: String): Message?
}

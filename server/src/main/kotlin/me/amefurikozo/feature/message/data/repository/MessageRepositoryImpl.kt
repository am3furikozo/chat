package me.amefurikozo.feature.message.data.repository

import me.amefurikozo.feature.message.data.source.MessageDao
import me.amefurikozo.feature.message.domain.model.Message
import me.amefurikozo.feature.message.domain.repository.MessageRepository

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
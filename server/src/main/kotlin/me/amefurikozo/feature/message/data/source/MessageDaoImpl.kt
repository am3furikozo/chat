package me.amefurikozo.feature.message.data.source

import me.amefurikozo.feature.message.domain.model.Message
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
package me.amefurikozo.feature.room.presentation

import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.amefurikozo.feature.message.domain.model.Message
import me.amefurikozo.feature.message.domain.repository.MessageRepository
import me.amefurikozo.feature.room.domain.model.Member
import me.amefurikozo.feature.room.domain.model.MemberAlreadyExistsException
import java.util.concurrent.ConcurrentHashMap

class RoomController(private val messageRepository: MessageRepository) {
  private val members = ConcurrentHashMap<String, Member>()

  fun onJoin(username: String, sessionId: String, socket: WebSocketSession) {
    if (members.containsKey(username)) throw MemberAlreadyExistsException("Member with same name already exists.")
    members[username] = Member(
      username = username,
      sessionId = sessionId,
      socket = socket
    )
  }

  suspend fun sendMessage(senderName: String, text: String) {
    val message = Message(text = text, username = senderName, timestamp = System.currentTimeMillis())
    messageRepository.save(message)
    members.values.forEach { it.socket.send(Frame.Text(Json.encodeToString(message))) }
  }

  suspend fun getAllMessages(): List<Message> {
    return messageRepository.getAll()
  }

  suspend fun tryLeave(username: String) {
    val member = members[username]
    if (member != null) {
      member.socket.close()
      members.remove(username)
    }
  }
}
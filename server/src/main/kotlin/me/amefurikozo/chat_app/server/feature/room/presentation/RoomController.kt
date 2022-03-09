/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.feature.room.presentation

import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.amefurikozo.chat_app.server.feature.message.domain.model.Message
import me.amefurikozo.chat_app.server.feature.message.domain.repository.MessageRepository
import me.amefurikozo.chat_app.server.feature.room.domain.model.Member
import me.amefurikozo.chat_app.server.feature.room.domain.model.MemberAlreadyExistsException
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
    members.values.forEach { it.socket.send(Frame.Text(Json.encodeToString(message.toDto()))) }
  }

  suspend fun tryLeave(username: String) {
    val member = members[username]
    if (member != null) {
      member.socket.close()
      members.remove(username)
    }
  }
}
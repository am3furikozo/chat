/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.domain.service

import android.app.Application
import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.amefurikozo.chat_app.client.R
import me.amefurikozo.chat_app.client.common.domain.service.BaseService
import me.amefurikozo.chat_app.client.feature.chat.data.remote.dto.MessageDto
import me.amefurikozo.chat_app.client.feature.chat.domain.model.Message
import me.amefurikozo.chat_app.client.util.Resource

class ChatServiceImpl(app: Application, private val client: HttpClient) : BaseService(app), ChatService {
  companion object {
    const val HTTP_BASE_URL = "http://10.0.2.2:8082"
    const val WS_BASE_URL = "ws://10.0.2.2:8082"
  }

  sealed class HttpEndpoints(val url: String) {
    object GetAllMessages : HttpEndpoints("$HTTP_BASE_URL/messages")
  }

  sealed class WsEndpoints(val url: String) {
    object ChatSocket : WsEndpoints("$WS_BASE_URL/chat-socket")
  }

  private var socket: WebSocketSession? = null

  override suspend fun getAll(): List<Message> {
    return try {
      val result = client.get<List<MessageDto>>(HttpEndpoints.GetAllMessages.url).map { it.toMessage() }
      Log.w("BIG_TAG", result.toString())
      result
    } catch (e: Exception) {
      Log.e("BIG_TAG", "Error", e)
      emptyList()
    }
  }

  override suspend fun joinSession(username: String): Resource<Unit> {
    return try {
      socket = client.webSocketSession { url("${WsEndpoints.ChatSocket.url}?username=$username") }
      if (socket?.isActive == true) Resource.Success(Unit)
      else Resource.Error(stringResource(R.string.chat_chat_service_join_session_connection_not_established_message))
    } catch (e: Exception) {
      e.printStackTrace()
      Resource.Error(e.localizedMessage ?: stringResource(R.string.unknown_exception_message))
    }
  }

  override suspend fun sendMessage(message: String) {
    try {
      socket?.send(Frame.Text(message))
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun observeMessages(): Flow<Message> {
    var flow: Flow<Message>? = null
    try {
      flow = socket
        ?.incoming
        ?.receiveAsFlow()
        ?.filter { it is Frame.Text }
        ?.map { Json.decodeFromString<MessageDto>((it as? Frame.Text)?.readText() ?: "").toMessage() }
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return flow ?: emptyFlow()
  }

  override suspend fun leaveSession() {
    socket?.close()
    socket = null
  }
}
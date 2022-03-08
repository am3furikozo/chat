package me.amefurikozo.feature.room.domain.model

import io.ktor.http.cio.websocket.*

data class Member(
  val username: String,
  val sessionId: String,
  val socket: WebSocketSession
)

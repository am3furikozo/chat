package me.amefurikozo.plugins

import io.ktor.application.*
import io.ktor.sessions.*
import io.ktor.util.*
import me.amefurikozo.feature.session.domain.model.ChatSession

fun Application.configureSecurity() {
  install(Sessions) {
    cookie<ChatSession>("SESSION")
  }

  intercept(ApplicationCallPipeline.Features) {
    if (call.sessions.get<ChatSession>() == null) call.sessions.set(
        ChatSession(call.parameters["username"] ?: "Guest", generateNonce())
    )
  }
}

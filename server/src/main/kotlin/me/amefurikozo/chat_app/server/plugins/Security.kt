/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.plugins

import io.ktor.application.*
import io.ktor.sessions.*
import io.ktor.util.*
import me.amefurikozo.chat_app.server.feature.session.domain.model.ChatSession

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

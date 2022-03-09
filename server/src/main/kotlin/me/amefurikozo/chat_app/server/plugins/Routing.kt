/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.amefurikozo.chat_app.server.feature.message.presentation.MessageController
import me.amefurikozo.chat_app.server.feature.room.presentation.RoomController
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val messageController by inject<MessageController>()

    routing {
        get(path = "/messages") {
            call.respond(HttpStatusCode.OK, messageController.getAll())
        }
    }
}

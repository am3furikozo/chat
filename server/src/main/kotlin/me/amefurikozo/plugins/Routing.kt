package me.amefurikozo.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.amefurikozo.feature.room.presentation.RoomController
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()

    routing {
        get(path = "/messages") {
            call.respond(HttpStatusCode.OK, Json.encodeToString(roomController.getAllMessages()))
        }
    }
}

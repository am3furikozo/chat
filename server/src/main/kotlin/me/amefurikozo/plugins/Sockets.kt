package me.amefurikozo.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.sessions.*
import kotlinx.coroutines.channels.consumeEach
import me.amefurikozo.feature.room.domain.model.MemberAlreadyExistsException
import me.amefurikozo.feature.room.presentation.RoomController
import me.amefurikozo.feature.session.domain.model.ChatSession
import org.koin.ktor.ext.inject

fun Application.configureSockets() {
    val roomController by inject<RoomController>()

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/chat-socket") {
            val session = call.sessions.get<ChatSession>()
            if (session == null) {
                close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session."))
                return@webSocket
            }
            try {
                roomController.onJoin(username = session.username, sessionId = session.sessionId, socket = this)
                incoming.consumeEach {
                    if (it is Frame.Text)
                        roomController.sendMessage(senderName = session.username, text = it.readText())
                }
            } catch (e: MemberAlreadyExistsException) {
                call.respond(HttpStatusCode.Conflict)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                roomController.tryLeave(session.username)
            }
        }
    }
}

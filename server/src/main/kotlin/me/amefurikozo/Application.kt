package me.amefurikozo

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.amefurikozo.plugins.*

fun main() {
  embeddedServer(Netty, port = 8082) {
    configureIOC()
    configureSockets()
    configureRouting()
    configureMonitoring()
    configureSecurity()
  }.start(wait = true)
}
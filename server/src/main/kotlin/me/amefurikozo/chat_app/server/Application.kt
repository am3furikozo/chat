/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.amefurikozo.chat_app.server.plugins.*

fun main() {
  embeddedServer(Netty, port = 8082) {
    configureIOC()
    configureSockets()
    configureRouting()
    configureSecurity()
    configureMonitoring()
    configureSerialization()
  }.start(wait = true)
}
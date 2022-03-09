/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.plugins

import io.ktor.application.*
import me.amefurikozo.chat_app.server.di.appModule
import org.koin.ktor.ext.modules

fun Application.configureIOC() {
  modules(appModule)
}
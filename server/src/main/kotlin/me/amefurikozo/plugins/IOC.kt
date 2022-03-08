package me.amefurikozo.plugins

import io.ktor.application.*
import me.amefurikozo.di.appModule
import org.koin.ktor.ext.modules

fun Application.configureIOC() {
  modules(appModule)
}
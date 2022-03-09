/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import me.amefurikozo.chat_app.client.feature.chat.domain.service.ChatService
import me.amefurikozo.chat_app.client.feature.chat.domain.service.ChatServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideHttpClient(): HttpClient {
    return HttpClient(CIO) {
      install(Logging)
      install(WebSockets)
      install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
          prettyPrint = true
          isLenient = true
        })
      }
    }
  }

  @Provides
  @Singleton
  fun provideChatService(app: Application, client: HttpClient): ChatService {
    return ChatServiceImpl(app, client)
  }
}
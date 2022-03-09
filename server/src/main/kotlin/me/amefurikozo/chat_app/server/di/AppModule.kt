/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.di

import me.amefurikozo.chat_app.server.feature.message.data.repository.MessageRepositoryImpl
import me.amefurikozo.chat_app.server.feature.message.data.source.MessageDao
import me.amefurikozo.chat_app.server.feature.message.data.source.MessageDaoImpl
import me.amefurikozo.chat_app.server.feature.message.domain.repository.MessageRepository
import me.amefurikozo.chat_app.server.feature.message.presentation.MessageController
import me.amefurikozo.chat_app.server.feature.room.presentation.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val appModule = module {
  single {
    KMongo.createClient("mongodb://root:password@0.0.0.0:27017").coroutine.getDatabase("chat")
  }
  single<MessageDao> {
    MessageDaoImpl(get())
  }
  single<MessageRepository> {
    MessageRepositoryImpl(get())
  }
  single {
    MessageController(get())
  }
  single {
    RoomController(get())
  }
}
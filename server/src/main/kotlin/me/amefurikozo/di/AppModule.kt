package me.amefurikozo.di

import me.amefurikozo.feature.message.data.repository.MessageRepositoryImpl
import me.amefurikozo.feature.message.data.source.MessageDao
import me.amefurikozo.feature.message.data.source.MessageDaoImpl
import me.amefurikozo.feature.message.domain.repository.MessageRepository
import me.amefurikozo.feature.room.presentation.RoomController
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
    RoomController(get())
  }
}
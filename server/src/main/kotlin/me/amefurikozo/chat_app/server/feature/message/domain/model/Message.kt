/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.server.feature.message.domain.model

import kotlinx.serialization.Serializable
import me.amefurikozo.chat_app.server.feature.message.data.dto.MessageDto
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
  @BsonId val id: String = ObjectId().toHexString(),
  val text: String,
  val username: String,
  val timestamp: Long
) {
  fun toDto(): MessageDto {
    return MessageDto(id = id, text = text, username = username, timestamp = timestamp)
  }
}

/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.data.remote.dto

import kotlinx.serialization.Serializable
import me.amefurikozo.chat_app.client.feature.chat.domain.model.Message
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto(
  val id: String,
  val username: String,
  val text: String,
  val timestamp: Long,
) {
  fun toMessage(): Message {
    return Message(
      username = username,
      text = text,
      time = DateFormat.getDateInstance(DateFormat.DEFAULT).format(Date(timestamp))
    )
  }
}

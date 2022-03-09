/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import me.amefurikozo.chat_app.client.feature.chat.domain.model.Message
import me.amefurikozo.chat_app.client.ui.theme.BabyBlue
import me.amefurikozo.chat_app.client.ui.theme.CustomDarkGray
import me.amefurikozo.chat_app.client.ui.theme.RedPink

@Suppress("FunctionName")
@Composable
fun ChatMessage(modifier: Modifier, username: String?, message: Message) {
  val isOwnMessage = message.username == username
  val messageColor = if (isOwnMessage) Color.BabyBlue else Color.RedPink
  val messageCornerRadius = 10.dp
  Box(
    modifier = modifier,
    contentAlignment = if (isOwnMessage) Alignment.CenterEnd else Alignment.CenterStart
  ) {
    Column(
      modifier = Modifier
        .width(200.dp)
        .drawBehind {
          val triangleHeight = 20.dp.toPx()
          val triangleWidth = 25.dp.toPx()
          val trianglePath = Path().apply {
            if (isOwnMessage) {
              moveTo(size.width, size.height - messageCornerRadius.toPx())
              lineTo(size.width, size.height + triangleHeight)
              lineTo(size.width - triangleWidth, size.height - messageCornerRadius.toPx())
              close()
            } else {
              moveTo(0f, size.height - messageCornerRadius.toPx())
              lineTo(0f, size.height + triangleHeight)
              lineTo(triangleWidth, size.height - messageCornerRadius.toPx())
              close()
            }
          }
          drawPath(path = trianglePath, color = messageColor)
        }
        .background(color = messageColor, shape = RoundedCornerShape(messageCornerRadius))
        .padding(8.dp)
    ) {
      Text(
        text = message.username,
        color = Color.CustomDarkGray,
        style = MaterialTheme.typography.h5
      )
      Text(
        text = message.text,
        color = Color.CustomDarkGray,
        style = MaterialTheme.typography.body1
      )
      Text(
        modifier = Modifier.align(Alignment.End),
        text = message.time,
        color = Color.CustomDarkGray,
        style = MaterialTheme.typography.body2
      )
    }
  }
}
/*
 * *********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.feature.chat.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import me.amefurikozo.chat_app.client.R
import me.amefurikozo.chat_app.client.ui.theme.BabyBlue
import me.amefurikozo.chat_app.client.ui.theme.CustomDarkGray
import me.amefurikozo.chat_app.client.ui.theme.RedPink

@Suppress("FunctionName")
@Composable
fun ChatInput(
  modifier: Modifier,
  value: String,
  onChange: (String) -> Unit,
  onClick: () -> Unit
) {
  Row(modifier = modifier) {
    TextField(
      modifier = Modifier.weight(1f),
      value = value,
      colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.BabyBlue,
        textColor = Color.CustomDarkGray,
        cursorColor = Color.RedPink
      ),
      textStyle = MaterialTheme.typography.body1,
      shape = RoundedCornerShape(0),
      onValueChange = onChange,
      placeholder = { Text(text = stringResource(R.string.chat_component_input_text_placeholder)) }
    )
    IconButton(onClick = onClick) {
      Icon(
        imageVector = Icons.Default.Send,
        contentDescription = stringResource(R.string.chat_component_input_send_btn_description),
        tint = Color.RedPink
      )
    }
  }
}
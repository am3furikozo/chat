/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.common.domain.service

import android.app.Application
import androidx.annotation.StringRes

abstract class BaseService(private val app: Application) {
  private val context get() = app

  protected fun stringResource(@StringRes id: Int): String = context.getString(id)
}
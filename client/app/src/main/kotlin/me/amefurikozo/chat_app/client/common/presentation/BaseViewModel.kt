/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.common.presentation

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {
  private val context get() = getApplication<Application>()

  protected fun stringResource(@StringRes id: Int): String = context.getString(id)
}
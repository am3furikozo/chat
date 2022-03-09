/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

package me.amefurikozo.chat_app.client.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
  class Success<T>(data: T?) : Resource<T>(data)
  class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

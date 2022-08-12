package com.musicianhelper.core.common

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface ResourceProvider {

  fun getString(@StringRes resId: Int): String

  fun getString(
    @StringRes resId: Int,
    vararg args: Any
  ): String

  fun getStringArray(@ArrayRes resId: Int): Array<String>
}

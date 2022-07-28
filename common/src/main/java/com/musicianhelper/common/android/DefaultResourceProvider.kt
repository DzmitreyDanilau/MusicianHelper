package com.musicianhelper.common.android

import android.content.Context
import javax.inject.Inject

class DefaultResourceProvider @Inject constructor(
  private val context: Context
) : ResourceProvider {

  override fun getString(resId: Int): String {
    return context.getString(resId)
  }

  override fun getString(
    resId: Int,
    vararg args: Any
  ): String {
    return context.getString(resId, *args)
  }

  override fun getStringArray(resId: Int): Array<String> {
    return context.resources.getStringArray(resId)
  }
}

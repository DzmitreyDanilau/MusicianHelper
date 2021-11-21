package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.State

data class LoginState(
  val progress: Boolean,
  val success: Boolean,
  val error: String? = null
) : State

package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.State

sealed class LoginState(open var inProgress: Boolean) : State {
  object Initial : LoginState(inProgress = false)
  data class Success(override var inProgress: Boolean) : LoginState(inProgress)
  data class Fail(override var inProgress: Boolean, val error: Throwable?) : LoginState(inProgress)
}



package com.musicianhelper.login.impl.domain

import com.musicianhelper.core.common.data.User

sealed class LoginResult : com.musicianhelper.core.common.Result {
  data class Success(val user: User) : LoginResult()
  data class Fail(val error: Throwable) : LoginResult()
  object NavigateToRegisterResult : LoginResult()
  object Loading : LoginResult()
  object DismissResult : LoginResult()
}

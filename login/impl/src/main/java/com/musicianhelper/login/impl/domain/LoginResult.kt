package com.musicianhelper.login.impl.domain

import com.musicianhelper.common.Result
import com.musicianhelper.data.User

sealed class LoginResult : Result {
  data class Success(val user: User) : LoginResult()
  data class Fail(val error: Throwable) : LoginResult()
  object Loading : LoginResult()
}

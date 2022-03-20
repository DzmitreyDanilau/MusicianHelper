package com.musicianhelper.login.impl.login.ui

import com.musicianhelper.common.Action

sealed class LoginAction : Action {
  data class Login(val name: String, val password: String) : LoginAction()
}

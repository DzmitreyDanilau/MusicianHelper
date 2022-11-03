package com.musicianhelper.login.impl.ui

import com.musicianhelper.core.common.State

sealed interface LoginState : State {
  val isSnackBarVisible: Boolean
  val isSignUpVisible: Boolean
  val error: Throwable?
  val isLoading: Boolean

  object Initial : LoginState {
    override val isSnackBarVisible: Boolean = false
    override val isSignUpVisible: Boolean = false
    override val isLoading: Boolean = false
    override val error: Throwable? = null
  }

  object Success : LoginState {
    override val isSnackBarVisible: Boolean = false
    override val isSignUpVisible: Boolean = false
    override val isLoading: Boolean = false
    override val error: Throwable? = null
  }

  object Dismiss : LoginState {
    override val isSnackBarVisible: Boolean = false
    override val isSignUpVisible: Boolean = false
    override val isLoading: Boolean = false
    override val error: Throwable? = null
  }

  data class Fail(
    override val isSnackBarVisible: Boolean,
    override val isSignUpVisible: Boolean,
    override val isLoading: Boolean = false,
    override val error: Throwable?
  ) : LoginState

  data class Loading(
    override val isSignUpVisible: Boolean,
    override val isSnackBarVisible: Boolean
  ) : LoginState {
    override val error: Throwable? = null
    override val isLoading: Boolean = true
  }
}



package com.musicianhelper.login.impl.login.ui

import com.musicianhelper.common.State

sealed class LoginState : State {
    abstract val inProgress: Boolean
    abstract val isSnackBarVisible: Boolean
    abstract val isSignUpVisible: Boolean
    abstract val error: Throwable?

    object Initial : LoginState() {
        override val inProgress: Boolean = false
        override val isSnackBarVisible: Boolean = false
        override val isSignUpVisible: Boolean = false
        override val error: Throwable? = null
    }

    object Success : LoginState() {
        override val error: Throwable? = null
        override var inProgress: Boolean = false
        override val isSnackBarVisible: Boolean = false
        override val isSignUpVisible: Boolean = false
    }

    object Dismiss : LoginState() {
        override var inProgress: Boolean = false
        override val isSnackBarVisible: Boolean = false
        override val isSignUpVisible: Boolean = false
        override val error: Throwable? = null
    }

    data class Fail(
        override var inProgress: Boolean,
        override val isSnackBarVisible: Boolean,
        override val isSignUpVisible: Boolean,
        override val error: Throwable?
    ) : LoginState()
}



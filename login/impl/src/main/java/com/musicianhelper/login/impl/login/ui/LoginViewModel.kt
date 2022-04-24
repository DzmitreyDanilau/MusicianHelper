package com.musicianhelper.login.impl.login.ui

import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Navigation
import com.musicianhelper.common.Result
import com.musicianhelper.common.android.BaseViewModel
import com.musicianhelper.di.Main
import com.musicianhelper.login.impl.login.domain.Login
import com.musicianhelper.login.impl.login.domain.LoginResult
import com.musicianhelper.login.impl.login.domain.LoginResult.DismissResult
import com.musicianhelper.login.impl.login.domain.LoginResult.NavigateToRegisterResult
import com.musicianhelper.login.impl.login.ui.LoginState.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LoginViewModel @Inject constructor(
    @Main dispatcher: CoroutineDispatcher,
    private val loginUseCase: Login
) : BaseViewModel<LoginState>(initialState = Initial, dispatcher = dispatcher) {

    override fun reduceState(
        previous: LoginState,
        result: Result
    ): LoginState {
        return when (result) {
            is LoginResult.Success -> Success

            is LoginResult.Fail -> {
                Fail(
                    inProgress = false,
                    error = result.error,
                    isSnackBarVisible = true,
                    isSignUpVisible = true
                )
            }
            is DismissResult -> {
                Fail(
                    inProgress = previous.inProgress,
                    isSignUpVisible = previous.isSignUpVisible,
                    isSnackBarVisible = false,
                    error = previous.error
                )
            }
            is NavigateToRegisterResult -> {
                Fail(
                    inProgress = previous.inProgress,
                    isSnackBarVisible = false,
                    isSignUpVisible = false,
                    error = previous.error
                )
            }
            else -> previous
        }
    }

    override fun mapEventToAction(event: Event): Action {
        return when (event) {
            is LoginEvent.Login -> LoginAction.Login(event.name, event.password)
            is LoginEvent.DismissSnackbar -> SnackbarDismissAction
            is LoginEvent.SignUpClicked -> LoginAction.SignUpAction
            else -> object : Action {}
        }
    }

    override fun getNavigationByResult(result: Result): Navigation? {
        return when (result) {
            is NavigateToRegisterResult -> NavigateToRegistration
            else -> null
        }
    }

    override fun mapActionToResult(action: Action): Flow<Result> {
        return when (action) {
            is LoginAction.Login -> loginUseCase.invoke(action.name, action.password)
            is SnackbarDismissAction -> flowOf(DismissResult)
            is LoginAction.SignUpAction -> flowOf(NavigateToRegisterResult)
            else -> emptyFlow()
        }
    }
}

object SnackbarDismissAction : Action

object NavigateToRegistration : Navigation

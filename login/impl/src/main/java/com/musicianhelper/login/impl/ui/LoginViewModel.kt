package com.musicianhelper.login.impl.ui

import com.musicianhelper.core.common.Action
import com.musicianhelper.core.common.BaseViewModel
import com.musicianhelper.core.common.Event
import com.musicianhelper.core.common.Navigation
import com.musicianhelper.core.common.di.Main
import com.musicianhelper.core.common.domain.UseCase
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.domain.LoginResult.DismissResult
import com.musicianhelper.login.impl.domain.LoginResult.NavigateToRegisterResult
import com.musicianhelper.login.impl.ui.LoginState.Fail
import com.musicianhelper.login.impl.ui.LoginState.Initial
import com.musicianhelper.login.impl.ui.LoginState.Loading
import com.musicianhelper.login.impl.ui.LoginState.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

typealias Result = com.musicianhelper.core.common.Result

@ExperimentalCoroutinesApi
@FlowPreview
class LoginViewModel @Inject constructor(
  @Main dispatcher: CoroutineDispatcher,
  private val loginUseCase: UseCase<LoginAction, LoginResult>
) : BaseViewModel<LoginState>(initialState = Initial, dispatcher = dispatcher) {

  override fun reduceState(
    previous: LoginState,
    result: com.musicianhelper.core.common.Result
  ): LoginState {
    return when (result) {
      is LoginResult.Loading -> {
        Loading(
          isSignUpVisible = previous.isSignUpVisible,
          isSnackBarVisible = previous.isSnackBarVisible
        )
      }

      is LoginResult.Success -> Success

      is LoginResult.Fail -> {
        Fail(
          error = result.error,
          isSnackBarVisible = true,
          isSignUpVisible = true
        )
      }
      is DismissResult -> {
        Fail(
          isSignUpVisible = previous.isSignUpVisible,
          isSnackBarVisible = false,
          error = null
        )
      }
      is NavigateToRegisterResult -> {
        Fail(
          isSnackBarVisible = false,
          isSignUpVisible = false,
          error = null
        )
      }
      else -> previous
    }
  }

  override fun result(flow: Flow<Event>): Flow<Result> {
    return merge(
      getSharedActions(flow.map(::toAction)),
      flow.map(::toResult)
    )
  }

  private fun toAction(event: Event): Action {
    return when (event) {
      is LoginEvent.Login -> LoginAction(event.name, event.password)
      else -> object : Action {}
    }
  }

  override fun getNavigationByResult(result: Result): Navigation? {
    return when (result) {
      is NavigateToRegisterResult -> NavigateToRegistration
      else -> null
    }
  }

  override fun getSharedActions(action: Flow<Action>): Flow<Result> {
    return merge(
      action.filterIsInstance<LoginAction>().let {
        loginUseCase.apply(it)
      }
    )
  }

  private fun toResult(event: Event): Result {
    return when (event) {
      is LoginEvent.SignUpClicked -> NavigateToRegisterResult
      is LoginEvent.DismissSnackbar -> DismissResult
      else -> object : Result {}
    }
  }
}

object NavigateToRegistration : Navigation

package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Result
import com.musicianhelper.common.android.BaseViewModel
import com.musicianhelper.login.impl.domain.Login
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.domain.LoginResult.DismissResult
import com.musicianhelper.login.impl.ui.LoginState.Fail
import com.musicianhelper.login.impl.ui.LoginState.Initial
import com.musicianhelper.login.impl.ui.LoginState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LoginViewModel @Inject constructor(
  private val loginUseCase: Login
) : BaseViewModel<LoginState>(initialState = Initial) {

  override fun reduceState(previous: LoginState, result: Result): LoginState {
    return when (result) {
      is LoginResult.Success -> Success(inProgress = false)
      is LoginResult.Fail -> {
        Fail(
          inProgress = false,
          error = result.error
        )
      }
      is DismissResult -> Success(inProgress = previous.inProgress)

      else -> previous
    }
  }

  override fun mapEventToAction(event: Event): Action {
    return when (event) {
      is LoginEvent.Login -> LoginAction.Login(event.name, event.password)
      is LoginEvent.DismissSnackbar -> SnackbarDismissAction
      else -> object : Action {}
    }
  }

  override fun mapActionToResult(action: Action): Flow<Result> {
    return when (action) {
      is LoginAction.Login -> loginUseCase.invoke(action.name, action.password)
      is SnackbarDismissAction -> flowOf(DismissResult)
      else -> emptyFlow()
    }
  }
}

object SnackbarDismissAction : Action

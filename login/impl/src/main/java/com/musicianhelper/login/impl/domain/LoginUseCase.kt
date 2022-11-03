package com.musicianhelper.login.impl.domain

import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.domain.UseCase
import com.musicianhelper.login.impl.domain.LoginResult.Fail
import com.musicianhelper.login.impl.domain.LoginResult.Success
import com.musicianhelper.login.impl.ui.LoginAction
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(FlowPreview::class)
class LoginUseCase @Inject constructor(
  private val repository: Repository
) : UseCase<LoginAction, LoginResult> {

  override fun apply(upstream: Flow<LoginAction>): Flow<LoginResult> {
    return upstream.flatMapConcat { action ->
      repository.login(action.name, action.password)
        .map { result -> handleResult(result) }
        .onStart { emit(LoginResult.Loading) }
    }
  }

  private fun handleResult(result: Result<User>): LoginResult {
    return result.fold(
      onSuccess = { user -> Success(user) },
      onFailure = { error -> Fail(error) }
    )
  }
}

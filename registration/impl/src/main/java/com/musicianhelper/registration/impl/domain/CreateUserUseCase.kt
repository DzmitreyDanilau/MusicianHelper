package com.musicianhelper.registration.impl.domain

import com.musicianhelper.core.common.Action
import com.musicianhelper.core.common.domain.UseCase
import com.musicianhelper.data.api.UserCreator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
  private val repository: UserCreator
) : UseCase<CreateUserAction, CreateUserResult> {

  override fun apply(upstream: Flow<CreateUserAction>): Flow<CreateUserResult> {
    TODO("Not yet implemented")
  }
}

sealed class CreateUserResult : com.musicianhelper.core.common.Result

object CreateUserAction : Action

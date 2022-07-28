package com.mh.impl.domain

import com.musicianhelper.common.Action
import com.musicianhelper.common.Result
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.domain.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
  private val repository: UserCreator
) : UseCase<CreateUserAction, CreateUserResult> {

  override fun apply(upstream: Flow<CreateUserAction>): Flow<CreateUserResult> {
    TODO("Not yet implemented")
  }
}

sealed class CreateUserResult : Result

object CreateUserAction : Action

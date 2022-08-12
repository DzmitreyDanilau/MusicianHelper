package com.mh.impl.domain

import com.mh.impl.domain.RegistrationResult.Failed
import com.mh.impl.domain.RegistrationResult.Success
import com.musicianhelper.registration.impl.domain.repositories.RegistrationRepository
import com.musicianhelper.core.common.Action
import com.musicianhelper.core.common.data.UserData
import com.musicianhelper.core.common.domain.UseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(FlowPreview::class)
class RegisterUseCase @Inject constructor(
  private val repository: RegistrationRepository
) : UseCase<RegistrationAction, RegistrationResult> {

  override fun apply(upstream: Flow<RegistrationAction>): Flow<RegistrationResult> {
    return upstream.flatMapConcat { action ->
      repository.signIn(action.userData).map { result ->
        result.fold(
          onSuccess = { Success },
          onFailure = { Failed(it) }
        )
      }
    }
  }
}

sealed class RegistrationResult : com.musicianhelper.core.common.Result {
  object Success : RegistrationResult()
  data class Failed(val error: Throwable) : RegistrationResult()
}

data class RegistrationAction(val userData: UserData) : Action

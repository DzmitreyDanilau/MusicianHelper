package com.mh.impl

import com.mh.impl.RegistrationState.ShowPhotoSource
import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Result
import com.musicianhelper.common.android.BaseViewModel
import com.musicianhelper.di.Main
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationViewModel @Inject constructor(
  @Main private val dispatcher: CoroutineDispatcher
) : BaseViewModel<RegistrationState>(
  initialState = RegistrationState.Initial,
  dispatcher = dispatcher
) {

  override fun mapEventToAction(event: Event): Action {
    return when (event) {
      is ShowPickSourceDialogEvent -> ShowPickSourceDialogAction
      else -> object : Action {}
    }
  }

  override fun mapActionToResult(action: Action): Flow<Result> {
    return when (action) {
      is ShowPickSourceDialogAction -> flowOf(ShowPickSourceDialogResult)
      else -> emptyFlow()
    }
  }

  override fun reduceState(
    previous: RegistrationState,
    result: Result
  ): RegistrationState {
    return when (result) {
      is ShowPickSourceDialogResult -> ShowPhotoSource
      else -> previous
    }
  }
}

object ShowPickSourceDialogAction : Action

object ShowPickSourceDialogResult : Result

object ShowPickSourceDialogEvent : Event

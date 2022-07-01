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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationViewModel @Inject constructor(
  @Main private val dispatcher: CoroutineDispatcher
) : BaseViewModel<RegistrationState>(
  initialState = RegistrationState.Initial,
  dispatcher = dispatcher
) {

  override fun result(flow: Flow<Event>): Flow<Result> {
    return merge(flow.map(::toResult))
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

  override fun getSharedActions(action: Flow<Action>): Flow<Result> {
    return emptyFlow()
  }

  private fun toResult(event: Event): Result {
    return when (event) {
      is ShowPickSourceDialogEvent -> ShowPickSourceDialogResult
      is PictureSelected -> PicturePicked
      else -> object : Result {}
    }
  }
}

object ShowPickSourceDialogResult : Result
object PicturePicked : Result

object ShowPickSourceDialogEvent : Event
object PictureSelected : Event

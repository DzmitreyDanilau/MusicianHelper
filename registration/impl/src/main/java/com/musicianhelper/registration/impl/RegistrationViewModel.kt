package com.musicianhelper.registration.impl

import android.net.Uri
import com.mh.impl.domain.RegistrationAction
import com.mh.impl.domain.RegistrationResult
import com.mh.impl.domain.RegistrationResult.Success
import com.musicianhelper.core.common.Action
import com.musicianhelper.core.common.BaseViewModel
import com.musicianhelper.core.common.Event
import com.musicianhelper.core.common.Navigation
import com.musicianhelper.core.common.data.UserData
import com.musicianhelper.core.common.di.Main
import com.musicianhelper.core.common.domain.UseCase
import com.musicianhelper.registration.impl.RegistrationState.Default
import com.musicianhelper.registration.impl.RegistrationState.ShowPhotoSource
import com.musicianhelper.registration.impl.domain.GetFieldsAction
import com.musicianhelper.registration.impl.mappers.RegistrationFieldsMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

typealias Result = com.musicianhelper.core.common.Result

@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationViewModel @Inject constructor(
  @Main private val dispatcher: CoroutineDispatcher,
  private val mapper: RegistrationFieldsMapper,
  private val useCase: UseCase<RegistrationAction, RegistrationResult>
) : BaseViewModel<RegistrationState>(
  initialState = Default(emptyList()),
  dispatcher = dispatcher
) {

  override fun result(flow: Flow<Event>): Flow<com.musicianhelper.core.common.Result> {
    return merge(
      getSharedActions(
        flow
          .onStart { GetFieldsAction }
          .map(::toAction)),
      flow.map(::toResult)
    )
  }

  private fun toAction(event: Event): Action {
    return when (event) {
      is SignInEvent -> RegistrationAction(UserData("", event.email, event.password))
      else -> object : Action {}
    }
  }

  override fun reduceState(
    previous: RegistrationState,
    result: Result
  ): RegistrationState {
    return when (result) {
      is GetFieldsResult -> Default(mapper.map())
      is ShowPickSourceDialogResult -> ShowPhotoSource
      else -> previous
    }
  }

  override fun getSharedActions(action: Flow<Action>): Flow<Result> {
    return merge(
      action.filterIsInstance<RegistrationAction>().let {
        useCase.apply(it)
      },
      action.filterIsInstance<GetFieldsAction>().let {
        flowOf(GetFieldsResult)
      }
    )
  }

  override fun getNavigationByResult(result: Result): Navigation? {
    return when (result) {
      is Success -> NavigateToMain
      else -> null
    }
  }

  private fun toResult(event: Event): Result {
    return when (event) {
      is ShowPickSourceDialogEvent -> ShowPickSourceDialogResult
      is PictureSelected -> PicturePicked
      else -> object : Result {}
    }
  }
}

object NavigateToMain : Navigation

object GetFieldsResult : Result
object ShowPickSourceDialogResult : Result
object PicturePicked : Result

object ShowPickSourceDialogEvent : Event
object PictureSelected : Event

data class SignInEvent(
  val email: String,
  val password: String,
  val pictureUri: Uri? = null
) : Event

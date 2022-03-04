package com.musicianhelper.common.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Result
import com.musicianhelper.common.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseViewModel<S : State>(
  private val initialState: S,
  private val dispatcher: CoroutineDispatcher
) : ViewModel() {

  private val stateFlow = MutableStateFlow(initialState)
  private val actionFlow = MutableSharedFlow<Action>()

  init {
    initState()
  }

  private fun initState() {
    actionFlow
      .flatMapMerge(transform = ::mapActionToResult)
      .distinctUntilChanged()
      .scan(initialState, ::reduceState)
      .onEach { stateFlow.emit(it) }
      .launchIn(viewModelScope)
  }

  private fun processEvent(event: Flow<Event>) {
    event.onEach {
      actionFlow.emit(mapEventToAction(it))
    }.launchIn(viewModelScope)
  }

  fun dispatch(event: Event) {
    processEvent(flowOf(event))
  }

  fun observeState(): StateFlow<S> {
    return stateFlow
  }

  abstract fun mapEventToAction(event: Event): Action

  abstract fun mapActionToResult(action: Action): Flow<Result>

  abstract fun reduceState(
    previous: S,
    result: Result
  ): S
}

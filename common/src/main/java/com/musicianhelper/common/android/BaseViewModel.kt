package com.musicianhelper.common.android

import androidx.lifecycle.ViewModel
import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Result
import com.musicianhelper.common.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseViewModel<S : State, E : Event, A : Action, R : Result>(
  private val initialState: S
) : ViewModel() {

  private val viewModelCoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

  private val stateFlow = MutableStateFlow(initialState)
  private val eventFlow = MutableSharedFlow<A>()

  init {
    initState()
  }

  private fun initState() {
    eventFlow
      .flatMapMerge(transform = ::mapActionToResult)
      .scan(initialState, ::reduceState)
      .onEach(stateFlow::emit)
      .launchIn(viewModelCoroutineScope)
  }

  private fun processEvent(event: Flow<E>) {
    event.onEach {
      eventFlow.emit(mapEventToAction(it))
    }.launchIn(viewModelCoroutineScope)
  }

  override fun onCleared() {
    viewModelCoroutineScope.cancel()
    super.onCleared()
  }

  fun dispatch(event: E) {
    processEvent(flowOf(event))
  }

  fun observeState(): StateFlow<S> {
    return stateFlow
  }

  abstract fun mapEventToAction(event: E): A

  abstract fun mapActionToResult(action: A): Flow<R>

  abstract fun reduceState(previous: S, result: R): S
}

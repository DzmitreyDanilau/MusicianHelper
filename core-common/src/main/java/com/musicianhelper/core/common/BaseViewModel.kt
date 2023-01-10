package com.musicianhelper.core.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State>(
  private val initialState: S,
  private val dispatcher: CoroutineDispatcher
) : ViewModel(), DefaultLifecycleObserver {

  private val stateFlow = MutableStateFlow(initialState)
  private val actionFlow = MutableSharedFlow<Action>()
  private val navigationFlow = MutableSharedFlow<Navigation>()
  private val eventFLow = MutableSharedFlow<Event>()

  override fun onResume(owner: LifecycleOwner) {
    initState(eventFLow)
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    viewModelScope.coroutineContext[Job]?.cancelChildren()
  }

  private fun initState(eventFlow: Flow<Event>) {
    result(eventFlow)
      .onEach(::navigate)
      .filterNot { it is Navigation }
      .scan(initialState, ::reduceState)
      .distinctUntilChanged()
      .onEach { stateFlow.emit(it) }
      .launchIn(viewModelScope)
  }

  private suspend fun navigate(result: Result) {
    val navigation = getNavigationByResult(result)
    navigation?.let {
      navigationFlow.emit(navigation)
    }
  }

  fun dispatchEvent(event: Event) {
    viewModelScope.launch {
      eventFLow.emit(event)
    }
  }

  fun collectState(): StateFlow<S> = stateFlow

  fun collectNavigation(): SharedFlow<Navigation> = navigationFlow

  protected abstract fun result(flow: Flow<Event>): Flow<Result>

  protected abstract fun getSharedActions(action: Flow<Action>): Flow<Result>

  protected open fun getNavigationByResult(result: Result): Navigation? = null

  abstract fun reduceState(
    previous: S,
    result: Result
  ): S
}

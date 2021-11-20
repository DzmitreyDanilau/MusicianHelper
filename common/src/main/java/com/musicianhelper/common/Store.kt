package com.musicianhelper.common

import kotlinx.coroutines.flow.StateFlow

interface State
interface Action

interface Store<S : State, A : Action> {

  fun observeState(): StateFlow<S>

  fun dispatch(action: A)
}

package com.musicianhelper.common

import kotlinx.coroutines.flow.StateFlow

interface StoreState
interface Action

interface Store<S : StoreState, A : Action> {

  fun observeState(): StateFlow<S>

  fun dispatch(action: A)
}

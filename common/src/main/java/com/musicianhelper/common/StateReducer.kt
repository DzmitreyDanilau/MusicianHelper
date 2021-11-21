package com.musicianhelper.common

interface StateReducer<R : Result, S : State> {

  fun reduceState(previous: State, result: R): S
}

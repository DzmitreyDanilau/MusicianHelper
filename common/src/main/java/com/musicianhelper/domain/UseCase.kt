package com.musicianhelper.domain

import com.musicianhelper.common.Action
import com.musicianhelper.common.Result
import kotlinx.coroutines.flow.Flow

interface UseCase<A : Action, R : Result> {
  fun apply(upstream: Flow<A>): Flow<R>
}
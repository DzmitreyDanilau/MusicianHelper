package com.musicianhelper.core.common.domain

import com.musicianhelper.core.common.Action
import kotlinx.coroutines.flow.Flow

interface UseCase<A : Action, R : com.musicianhelper.core.common.Result > {
  fun apply(upstream: Flow<A>): Flow<R>
}
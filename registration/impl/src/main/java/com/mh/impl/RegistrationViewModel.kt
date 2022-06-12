package com.mh.impl

import com.musicianhelper.common.Action
import com.musicianhelper.common.Event
import com.musicianhelper.common.Result
import com.musicianhelper.common.android.BaseViewModel
import com.musicianhelper.di.Main
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RegistrationViewModel(
        @Main private val dispatcher: CoroutineDispatcher
) : BaseViewModel<RegistrationState>(
        initialState = RegistrationState.Initial,
        dispatcher = dispatcher
) {

    override fun mapEventToAction(event: Event): Action {
        TODO("Not yet implemented")
    }

    override fun mapActionToResult(action: Action): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun reduceState(previous: RegistrationState, result: Result): RegistrationState {
        TODO("Not yet implemented")
    }
}
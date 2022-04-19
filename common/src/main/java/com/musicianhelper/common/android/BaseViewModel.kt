package com.musicianhelper.common.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicianhelper.common.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseViewModel<S : State>(
    private val initialState: S,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val stateFlow = MutableStateFlow(initialState)
    private val actionFlow = MutableSharedFlow<Action>()
    private val navigationFlow = MutableSharedFlow<Navigation>()

    init {
        initState()
    }

    private fun initState() {
        actionFlow
            .flatMapMerge(transform = ::mapActionToResult)
            .distinctUntilChanged()
            .onEach(::navigate)
            .filterNot { it is Navigation }
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

    fun collectState(): StateFlow<S> {
        return stateFlow
    }

    fun collectNavigation(): SharedFlow<Navigation> = navigationFlow

    private suspend fun navigate(result: Result) {
        val navigation = getNavigationByResult(result)
        navigation?.let {
            navigationFlow.emit(navigation)
        }
    }

    protected open fun getNavigationByResult(result: Result): Navigation? {
        return null
    }

    abstract fun mapEventToAction(event: Event): Action

    abstract fun mapActionToResult(action: Action): Flow<Result>

    abstract fun reduceState(
        previous: S,
        result: Result
    ): S
}

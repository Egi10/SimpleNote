package com.bajapuik.simple_note.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<ScreenType, UiActionType> : ViewModel() {
    private val _navigatorTarget = MutableSharedFlow<ScreenType>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigatorTarget get() = _navigatorTarget.asSharedFlow()

    protected fun navigateTo(screen: ScreenType) {
        _navigatorTarget.tryEmit(screen)
    }

    abstract fun onEvent(event: UiActionType)
}
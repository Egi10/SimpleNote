package com.bajapuik.simple_note.screen.pin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.bajapuik.simple_note.base.BaseViewModel
import com.bajapuik.simple_note.domain.usecase.configuration.IsPinExistUseCase
import com.bajapuik.simple_note.domain.usecase.configuration.PinManagementUseCase
import com.bajapuik.simple_note.domain.utils.NotesResult
import com.bajapuik.simple_note.screen.pin.events.PinEvents
import com.bajapuik.simple_note.screen.pin.navigation.PinNavigationEvents
import kotlinx.coroutines.launch

class PinViewModel(
    private val isPinExistUseCase: IsPinExistUseCase,
    private val pinManagementUseCase: PinManagementUseCase
): BaseViewModel<PinNavigationEvents, PinEvents>() {
    var isExistPin by mutableStateOf(false)
        private set

    var pin by mutableStateOf("")
        private set

    var isErrorPin by mutableStateOf(false)
        private set

    override fun onEvent(event: PinEvents) {
        when(event) {
            is PinEvents.OnPinChange -> {
                pin = event.value
                isErrorPin = false

                if (event.isComplete) {
                    setPinManagement()
                }
            }
        }
    }

    fun getExistPin() {
        viewModelScope.launch {
            isExistPin = when(val result = isPinExistUseCase.invoke()) {
                is NotesResult.Success -> {
                    result.data
                }

                is NotesResult.Error -> {
                    false
                }
            }
        }
    }

    private fun setPinManagement() {
        viewModelScope.launch {
            val result = pinManagementUseCase.invoke(
                value = pin
            )

            when(result) {
                is NotesResult.Success -> {
                    if (result.data) {
                        navigateTo(
                            screen = PinNavigationEvents.OnHomeNavigation
                        )
                    } else {
                        isErrorPin = true
                    }
                }

                is NotesResult.Error -> {
                    isErrorPin = true
                }
            }
        }
    }
}
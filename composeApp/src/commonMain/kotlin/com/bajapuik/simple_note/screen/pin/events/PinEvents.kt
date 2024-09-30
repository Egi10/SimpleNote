package com.bajapuik.simple_note.screen.pin.events

typealias OnPinEvents = (PinEvents) -> Unit

sealed class PinEvents {
    data class OnPinChange(val value: String, val isComplete: Boolean): PinEvents()
}
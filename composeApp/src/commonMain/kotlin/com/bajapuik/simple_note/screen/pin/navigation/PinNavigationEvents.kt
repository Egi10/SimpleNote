package com.bajapuik.simple_note.screen.pin.navigation

typealias OnPinNavigationEvents = (PinNavigationEvents) -> Unit

sealed class PinNavigationEvents {
    data object OnHomeNavigation : PinNavigationEvents()
}
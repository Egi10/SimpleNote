package com.bajapuik.simple_note.screen.main.navigation

import com.bajapuik.simple_note.domain.model.Notes

typealias OnMainNavigationsEvents = (MainNavigationsEvents) -> Unit

sealed class MainNavigationsEvents {
    data class OnSelectNoteNavigation(val note: Notes) : MainNavigationsEvents()
    data object OnAddNoteNavigation : MainNavigationsEvents()
}
package com.bajapuik.simple_note.screen.main.events

import com.bajapuik.simple_note.domain.model.Notes

typealias OnMainEvents = (MainEvents) -> Unit

sealed class MainEvents {
    data class OnItemClick(val note: Notes) : MainEvents()
    data object OnAddClick : MainEvents()
    data object OnRetryNotes : MainEvents()
}
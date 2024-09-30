package com.bajapuik.simple_note.screen.main.state

import com.bajapuik.simple_note.domain.model.Notes

sealed interface NotesUiState {
    data object Loading : NotesUiState
    data object Empty : NotesUiState
    data object Failed : NotesUiState
    data class Success(
        val notes: List<Notes>
    ) : NotesUiState
}
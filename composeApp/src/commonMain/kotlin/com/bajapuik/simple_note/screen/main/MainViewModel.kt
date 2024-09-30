package com.bajapuik.simple_note.screen.main

import androidx.lifecycle.viewModelScope
import com.bajapuik.simple_note.base.BaseViewModel
import com.bajapuik.simple_note.domain.usecase.notes.GetNotesUseCase
import com.bajapuik.simple_note.domain.utils.NotesResult
import com.bajapuik.simple_note.screen.main.events.MainEvents
import com.bajapuik.simple_note.screen.main.navigation.MainNavigationsEvents
import com.bajapuik.simple_note.screen.main.state.NotesUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    getNotesUseCase: GetNotesUseCase
) : BaseViewModel<MainNavigationsEvents, MainEvents>() {

    override fun onEvent(event: MainEvents) {
        when(event) {
            MainEvents.OnAddClick -> {
                navigateTo(
                    screen = MainNavigationsEvents.OnAddNoteNavigation
                )
            }

            is MainEvents.OnItemClick -> {
                navigateTo(
                    screen = MainNavigationsEvents.OnSelectNoteNavigation(
                        note = event.note
                    )
                )
            }

            is MainEvents.OnRetryNotes -> {
                notesState
            }
        }
    }

    val notesState: StateFlow<NotesUiState> = getNotesUseCase.invoke()
        .map { result ->
            when (result) {
                is NotesResult.Success -> {
                    val notes = result.data

                    if (notes.isEmpty()) {
                        NotesUiState.Empty
                    } else {
                        NotesUiState.Success(
                            notes = notes
                        )
                    }
                }

                is NotesResult.Error -> {
                    NotesUiState.Failed
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotesUiState.Loading
        )
}
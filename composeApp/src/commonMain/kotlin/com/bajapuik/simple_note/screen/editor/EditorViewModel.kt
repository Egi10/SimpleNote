package com.bajapuik.simple_note.screen.editor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bajapuik.simple_note.base.BaseViewModel
import com.bajapuik.simple_note.domain.model.NotesParam
import com.bajapuik.simple_note.domain.usecase.notes.DeleteNotesByIdUseCase
import com.bajapuik.simple_note.domain.usecase.notes.SetNotesUseCase
import com.bajapuik.simple_note.domain.usecase.notes.UpdateNotesByIdUseCase
import com.bajapuik.simple_note.domain.utils.NotesResult
import com.bajapuik.simple_note.screen.editor.events.EditorEvents
import com.bajapuik.simple_note.screen.editor.navigation.EditorNavigationsEvents
import kotlinx.coroutines.launch

class EditorViewModel(
    private val setNotesUseCase: SetNotesUseCase,
    private val updateNotesByIdUseCase: UpdateNotesByIdUseCase,
    private val deleteNotesByIdUseCase: DeleteNotesByIdUseCase
) : BaseViewModel<EditorNavigationsEvents, EditorEvents>() {

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set

    override fun onEvent(event: EditorEvents) {
        when (event) {
            is EditorEvents.OnTitleInputChange -> {
                title = event.value
            }

            is EditorEvents.OnContentInputChange -> {
                content = event.value
            }

            is EditorEvents.OnDeleteNoteClick -> {
                deleteById(
                    id = event.id
                )
            }

            is EditorEvents.OnSaveNoteClick -> {
                val id = event.id

                if (id != null) {
                    updateNotes(
                        id = id,
                        title = title,
                        content = content
                    )
                } else {
                    setNotes(
                        title = title,
                        content = content
                    )
                }
            }

            EditorEvents.OnBackClick -> {
                navigateTo(
                    screen = EditorNavigationsEvents.OnBackNavigation
                )
            }
        }
    }

    private fun setNotes(
        title: String,
        content: String
    ) {
        viewModelScope.launch {
            val result = setNotesUseCase.invoke(
                params = NotesParam(
                    title = title,
                    content = content
                )
            )

            when (result) {
                is NotesResult.Success -> {
                    navigateTo(
                        screen = EditorNavigationsEvents.OnSuccessNavigation
                    )
                }

                is NotesResult.Error -> {
                    // TODO: Failed
                }
            }
        }
    }

    private fun deleteById(
        id: Int
    ) {
        viewModelScope.launch {
            val result = deleteNotesByIdUseCase.invoke(
                id = id
            )

            when (result) {
                is NotesResult.Success -> {
                    navigateTo(
                        screen = EditorNavigationsEvents.OnSuccessNavigation
                    )
                }

                is NotesResult.Error -> {
                    // TODO: Failed
                }
            }
        }
    }

    private fun updateNotes(
        id: Int,
        title: String,
        content: String
    ) {
        viewModelScope.launch {
            val result = updateNotesByIdUseCase.invoke(
                id = id,
                params = NotesParam(
                    title = title,
                    content = content
                )
            )

            when (result) {
                is NotesResult.Success -> {
                    navigateTo(
                        screen = EditorNavigationsEvents.OnSuccessNavigation
                    )
                }

                is NotesResult.Error -> {
                    // TODO: Failed
                }
            }
        }
    }
}
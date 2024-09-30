package com.bajapuik.simple_note.domain.usecase.notes

import com.bajapuik.simple_note.domain.model.Notes
import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import com.bajapuik.simple_note.domain.utils.NotesResult
import kotlinx.coroutines.flow.*

class GetNotesUseCase(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<NotesResult<List<Notes>>> {
        return notesRepository.getNotes()
            .map<List<Notes>, NotesResult<List<Notes>>> {
                return@map NotesResult.Success(it)
            }.catch { exception ->
                emit(NotesResult.Error(exception))
            }
    }
}
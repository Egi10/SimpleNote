package com.bajapuik.simple_note.domain.usecase.notes

import com.bajapuik.simple_note.domain.model.NotesParam
import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import com.bajapuik.simple_note.domain.utils.NotesResult

class SetNotesUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(params: NotesParam): NotesResult<Unit> {
        return try {
            require(params.title.isNotEmpty()) {
                "Title cannot be empty"
            }

            notesRepository.setNotes(
                params = params
            )

            NotesResult.Success(Unit)
        } catch (e: Exception) {
            NotesResult.Error(e)
        }
    }
}
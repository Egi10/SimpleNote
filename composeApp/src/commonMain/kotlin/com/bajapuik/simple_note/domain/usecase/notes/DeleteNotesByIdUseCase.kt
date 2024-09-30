package com.bajapuik.simple_note.domain.usecase.notes

import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import com.bajapuik.simple_note.domain.utils.NotesResult

class DeleteNotesByIdUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int): NotesResult<Unit> {
        return try {
            notesRepository.deleteById(
                id = id
            )

            NotesResult.Success(Unit)
        } catch (e: Exception) {
            NotesResult.Error(e)
        }
    }
}
package com.bajapuik.simple_note.domain.usecase.notes

import com.bajapuik.simple_note.domain.model.NotesParam
import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import com.bajapuik.simple_note.domain.utils.NotesResult

class UpdateNotesByIdUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int, params: NotesParam): NotesResult<Unit> {
        return try {
            notesRepository.updateById(
                id = id,
                params = params
            )

            NotesResult.Success(Unit)
        } catch (e: Exception) {
            NotesResult.Error(e)
        }
    }
}
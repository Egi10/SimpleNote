package com.bajapuik.simple_note.domain.repository.notes

import com.bajapuik.simple_note.domain.model.Notes
import com.bajapuik.simple_note.domain.model.NotesParam
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun setNotes(params: NotesParam)
    fun getNotes(): Flow<List<Notes>>
    suspend fun deleteById(id: Int)
    suspend fun updateById(id: Int, params: NotesParam)
}
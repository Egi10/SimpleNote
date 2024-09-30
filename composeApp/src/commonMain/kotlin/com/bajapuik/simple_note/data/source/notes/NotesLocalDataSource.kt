package com.bajapuik.simple_note.data.source.notes

import com.bajapuik.simple_note.core.database.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {
    suspend fun insert(notesEntity: NotesEntity)
    fun getNotes(): Flow<List<NotesEntity>>
    suspend fun deleteById(id: Int)
    suspend fun updateById(notesEntity: NotesEntity)
}
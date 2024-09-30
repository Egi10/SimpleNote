package com.bajapuik.simple_note.data.repository.notes

import com.bajapuik.simple_note.core.commons.DateTimeHelpers
import com.bajapuik.simple_note.core.database.entity.NotesEntity
import com.bajapuik.simple_note.data.repository.notes.mapper.asNotesDomainModel
import com.bajapuik.simple_note.data.source.notes.NotesLocalDataSource
import com.bajapuik.simple_note.domain.model.Notes
import com.bajapuik.simple_note.domain.model.NotesParam
import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
): NotesRepository {
    override suspend fun setNotes(params: NotesParam) {
        notesLocalDataSource.insert(
            notesEntity = NotesEntity(
                title = params.title,
                content = params.content,
                createdAt = DateTimeHelpers.getCurrentDateTime(),
                updatedAt = DateTimeHelpers.getCurrentDateTime()
            )
        )
    }

    override fun getNotes(): Flow<List<Notes>> {
        return notesLocalDataSource.getNotes()
            .map {
                it.asNotesDomainModel()
            }
    }

    override suspend fun deleteById(id: Int) {
        notesLocalDataSource.deleteById(
            id = id
        )
    }

    override suspend fun updateById(id: Int, params: NotesParam) {
        notesLocalDataSource.updateById(
            notesEntity = NotesEntity(
                id = id,
                title = params.title,
                content = params.content,
                createdAt = DateTimeHelpers.getCurrentDateTime(),
                updatedAt = DateTimeHelpers.getCurrentDateTime()
            )
        )
    }

}
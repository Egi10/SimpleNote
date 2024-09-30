package com.bajapuik.simple_note.data.source.notes

import com.bajapuik.simple_note.core.database.dao.NotesDao
import com.bajapuik.simple_note.core.database.entity.NotesEntity
import com.bajapuik.simple_note.data.source.notes.NotesLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class NotesLocalDataSourceImpl(
    private val notesDao: NotesDao,
    private val dispatcher: CoroutineDispatcher
): NotesLocalDataSource {
    override suspend fun insert(notesEntity: NotesEntity) = withContext(dispatcher) {
        notesDao.insert(notesEntity)
    }

    override fun getNotes(): Flow<List<NotesEntity>> {
        return notesDao.getNotes()
            .flowOn(dispatcher)
    }

    override suspend fun deleteById(id: Int) = withContext(dispatcher) {
        notesDao.deleteById(
            id = id
        )
    }

    override suspend fun updateById(notesEntity: NotesEntity) = withContext(dispatcher) {
        notesDao.updateById(
            title = notesEntity.title,
            content = notesEntity.content,
            updatedAt = notesEntity.updatedAt,
            id = notesEntity.id
        )
    }

}
package com.bajapuik.simple_note.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bajapuik.simple_note.core.database.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg notesEntity: NotesEntity)

    @Query("SELECT * FROM notesEntity")
    fun getNotes(): Flow<List<NotesEntity>>

    @Query("DELETE FROM notesEntity WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("UPDATE notesEntity SET title = :title, content = :content, updated_at = :updatedAt WHERE id = :id")
    suspend fun updateById(title: String, content: String, updatedAt: String, id: Int)
}
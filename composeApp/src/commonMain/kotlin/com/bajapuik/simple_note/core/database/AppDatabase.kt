package com.bajapuik.simple_note.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.bajapuik.simple_note.core.database.dao.NotesDao
import com.bajapuik.simple_note.core.database.entity.NotesEntity

@Database(
    entities = [NotesEntity::class],
    version = 1
)
@ConstructedBy(value = AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun notesDao(): NotesDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
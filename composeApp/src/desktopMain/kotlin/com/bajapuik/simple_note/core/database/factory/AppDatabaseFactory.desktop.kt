package com.bajapuik.simple_note.core.database.factory

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bajapuik.simple_note.core.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class AppDatabaseFactory actual constructor() {
    actual fun create(dbFileName: String): AppDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), dbFileName)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath
        ).setDriver(driver = BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
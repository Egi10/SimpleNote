package com.bajapuik.simple_note.core.database.factory

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bajapuik.simple_note.core.database.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class AppDatabaseFactory actual constructor() {
    actual fun create(dbFileName: String): AppDatabase {
        val dbFilePath = "${documentDirectory()}/$dbFileName"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath
        ).setDriver(driver = BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )

        return requireNotNull(documentDirectory?.path)
    }
}
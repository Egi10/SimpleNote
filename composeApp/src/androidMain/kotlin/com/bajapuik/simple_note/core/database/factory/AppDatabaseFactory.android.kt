package com.bajapuik.simple_note.core.database.factory

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bajapuik.simple_note.core.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class AppDatabaseFactory actual constructor(): KoinComponent {
    private val context by inject<Context>()
    
    actual fun create(dbFileName: String): AppDatabase {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        ).setDriver(driver = BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
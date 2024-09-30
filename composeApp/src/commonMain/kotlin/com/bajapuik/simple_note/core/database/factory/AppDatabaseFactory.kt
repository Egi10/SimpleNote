package com.bajapuik.simple_note.core.database.factory

import com.bajapuik.simple_note.core.database.AppDatabase

expect class AppDatabaseFactory() {
    fun create(dbFileName: String): AppDatabase
}
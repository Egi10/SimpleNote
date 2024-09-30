package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.core.database.AppDatabase
import com.bajapuik.simple_note.core.database.factory.AppDatabaseFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::AppDatabaseFactory)

    single {
        get<AppDatabaseFactory>().create(
            dbFileName = "bajapuik_notes.db"
        )
    }

    single {
        get<AppDatabase>().notesDao()
    }
}
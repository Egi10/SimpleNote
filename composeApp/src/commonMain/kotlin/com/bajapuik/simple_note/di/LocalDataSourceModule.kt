package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.data.source.configurations.ConfigurationsLocaDataSource
import com.bajapuik.simple_note.data.source.configurations.ConfigurationsLocalDataSourceImpl
import com.bajapuik.simple_note.data.source.notes.NotesLocalDataSource
import com.bajapuik.simple_note.data.source.notes.NotesLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localDataSourceModule = module {
    single<NotesLocalDataSource> {
        NotesLocalDataSourceImpl(
            notesDao = get(),
            dispatcher = get(
                qualifier = named(Dispatcher.IO)
            )
        )
    }

    single<ConfigurationsLocaDataSource> {
        ConfigurationsLocalDataSourceImpl(
            dataStoreManager = get(),
            dispatcher = get(
                qualifier = named(Dispatcher.IO)
            )
        )
    }
}
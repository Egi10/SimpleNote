package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.data.repository.configuration.ConfigurationRepositoryImpl
import com.bajapuik.simple_note.data.repository.notes.NotesRepositoryImpl
import com.bajapuik.simple_note.domain.repository.configuration.ConfigurationRepository
import com.bajapuik.simple_note.domain.repository.notes.NotesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NotesRepositoryImpl) {
        bind<NotesRepository>()
    }

    singleOf(::ConfigurationRepositoryImpl) {
        bind<ConfigurationRepository>()
    }
}
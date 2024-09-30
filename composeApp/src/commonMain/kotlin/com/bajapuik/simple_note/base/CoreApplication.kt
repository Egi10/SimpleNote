package com.bajapuik.simple_note.base

import com.bajapuik.simple_note.di.*
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object CoreApplication {
    fun initialize(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(
            corotutinesDispatcherModule,
            databaseModule,
            dataStoreModule,
            localDataSourceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}
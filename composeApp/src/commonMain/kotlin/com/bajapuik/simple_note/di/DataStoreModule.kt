package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.core.datastore.DataStoreFactory
import com.bajapuik.simple_note.core.datastore.DataStoreManager
import org.koin.dsl.module

val dataStoreModule = module {
    single {
        DataStoreFactory.createDataStore()
    }

    single {
        DataStoreManager(
            dataStore = get()
        )
    }
}
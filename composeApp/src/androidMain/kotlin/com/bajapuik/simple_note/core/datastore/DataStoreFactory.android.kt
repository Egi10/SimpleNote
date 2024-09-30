package com.bajapuik.simple_note.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object DataStoreFactory : KoinComponent {
    private val context by inject<Context>()

    actual fun createDataStore(): DataStore<Preferences> {
        return createDataStore(
            producePath = {
                context.filesDir.resolve(dataStoreFileName).absolutePath
            }
        )
    }
}
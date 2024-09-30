package com.bajapuik.simple_note.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual object DataStoreFactory {
    actual fun createDataStore(): DataStore<Preferences> {
        return createDataStore(
            producePath = {
                dataStoreFileName
            }
        )
    }
}
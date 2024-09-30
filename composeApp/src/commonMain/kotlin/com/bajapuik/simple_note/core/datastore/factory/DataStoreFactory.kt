package com.bajapuik.simple_note.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = {
        producePath().toPath()
    }
)

internal const val dataStoreFileName = "simplenote.preferences_pb"

expect object DataStoreFactory {
    fun createDataStore(): DataStore<Preferences>
}
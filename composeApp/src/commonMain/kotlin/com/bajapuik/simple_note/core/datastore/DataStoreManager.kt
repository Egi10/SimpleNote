package com.bajapuik.simple_note.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun setString(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String, defaultValue: String = ""): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: defaultValue
        }
    }

    suspend fun getFirstString(key: String, defaultValue: String = ""): String {
        return getString(
            key = key,
            defaultValue = defaultValue
        ).first()
    }
}
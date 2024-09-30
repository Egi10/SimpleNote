package com.bajapuik.simple_note.data.source.configurations

import com.bajapuik.simple_note.core.datastore.DataStoreManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ConfigurationsLocalDataSourceImpl(
    private val dataStoreManager: DataStoreManager,
    private val dispatcher: CoroutineDispatcher
): ConfigurationsLocaDataSource {
    override suspend fun setPin(value: String) = withContext(dispatcher) {
        dataStoreManager.setString(
            key = PIN_KEY,
            value = value
        )
    }

    override suspend fun getPin(): String = withContext(dispatcher) {
        return@withContext dataStoreManager.getFirstString(
            key = PIN_KEY
        )
    }

    companion object {
        private const val PIN_KEY = "pin"
    }
}
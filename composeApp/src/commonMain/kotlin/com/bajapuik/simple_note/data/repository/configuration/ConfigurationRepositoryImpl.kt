package com.bajapuik.simple_note.data.repository.configuration

import com.bajapuik.simple_note.data.source.configurations.ConfigurationsLocaDataSource
import com.bajapuik.simple_note.domain.repository.configuration.ConfigurationRepository

class ConfigurationRepositoryImpl(
    private val configurationsLocaDataSource: ConfigurationsLocaDataSource
): ConfigurationRepository {
    override suspend fun setPin(value: String) {
        configurationsLocaDataSource.setPin(
            value = value
        )
    }

    override suspend fun getPin(): String {
        return configurationsLocaDataSource.getPin()
    }

}
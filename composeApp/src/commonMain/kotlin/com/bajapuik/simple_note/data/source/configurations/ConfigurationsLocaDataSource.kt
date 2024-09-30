package com.bajapuik.simple_note.data.source.configurations

interface ConfigurationsLocaDataSource {
    suspend fun setPin(value: String)
    suspend fun getPin(): String
}
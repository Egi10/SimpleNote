package com.bajapuik.simple_note.domain.repository.configuration

interface ConfigurationRepository {
    suspend fun setPin(value: String)
    suspend fun getPin(): String
}
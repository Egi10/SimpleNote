package com.bajapuik.simple_note.domain.usecase.configuration

import com.bajapuik.simple_note.domain.repository.configuration.ConfigurationRepository

class ValidatePinUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(value: String): Boolean {
        val pin = configurationRepository.getPin()

        return pin == value
    }
}
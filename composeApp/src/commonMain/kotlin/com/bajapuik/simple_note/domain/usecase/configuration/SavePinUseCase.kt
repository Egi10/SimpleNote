package com.bajapuik.simple_note.domain.usecase.configuration

import com.bajapuik.simple_note.domain.repository.configuration.ConfigurationRepository

class SavePinUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(value: String) {
        configurationRepository.setPin(
            value = value
        )
    }
}
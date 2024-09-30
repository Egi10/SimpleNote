package com.bajapuik.simple_note.domain.usecase.configuration

import com.bajapuik.simple_note.domain.repository.configuration.ConfigurationRepository
import com.bajapuik.simple_note.domain.utils.NotesResult

class IsPinExistUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(): NotesResult<Boolean> {
        return try {
            val isPinExist = configurationRepository.getPin()
                .isNotEmpty()

            NotesResult.Success(isPinExist)
        } catch (e: Exception) {
            NotesResult.Error(e)
        }
    }
}
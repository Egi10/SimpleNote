package com.bajapuik.simple_note.domain.usecase.configuration

import co.touchlab.kermit.Logger
import com.bajapuik.simple_note.domain.utils.NotesResult

class PinManagementUseCase(
    private val isPinExistUseCase: IsPinExistUseCase,
    private val savePinUseCase: SavePinUseCase,
    private val validatePinUseCase: ValidatePinUseCase
) {
    suspend operator fun invoke(value: String): NotesResult<Boolean> {
        return try {
            when(val result = isPinExistUseCase.invoke()) {
                is NotesResult.Success -> {
                    if (!result.data) {
                        savePinUseCase.invoke(
                            value = value
                        )
                    }
                }

                is NotesResult.Error -> {
                    throw result.exception
                }
            }

            val validate = validatePinUseCase.invoke(
                value = value
            )

            NotesResult.Success(validate)
        } catch (e: Exception) {
            NotesResult.Error(e)
        }
    }
}
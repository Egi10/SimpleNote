package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.domain.usecase.notes.DeleteNotesByIdUseCase
import com.bajapuik.simple_note.domain.usecase.notes.GetNotesUseCase
import com.bajapuik.simple_note.domain.usecase.notes.SetNotesUseCase
import com.bajapuik.simple_note.domain.usecase.notes.UpdateNotesByIdUseCase
import com.bajapuik.simple_note.domain.usecase.configuration.IsPinExistUseCase
import com.bajapuik.simple_note.domain.usecase.configuration.PinManagementUseCase
import com.bajapuik.simple_note.domain.usecase.configuration.SavePinUseCase
import com.bajapuik.simple_note.domain.usecase.configuration.ValidatePinUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetNotesUseCase)
    factoryOf(::SetNotesUseCase)
    factoryOf(::UpdateNotesByIdUseCase)
    factoryOf(::DeleteNotesByIdUseCase)
    factoryOf(::IsPinExistUseCase)
    factoryOf(::PinManagementUseCase)
    factoryOf(::SavePinUseCase)
    factoryOf(::ValidatePinUseCase)
}
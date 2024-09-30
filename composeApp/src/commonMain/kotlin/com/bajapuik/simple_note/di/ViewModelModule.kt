package com.bajapuik.simple_note.di

import com.bajapuik.simple_note.screen.editor.EditorViewModel
import com.bajapuik.simple_note.screen.main.MainViewModel
import com.bajapuik.simple_note.screen.pin.PinViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::EditorViewModel)
    viewModelOf(::PinViewModel)
}

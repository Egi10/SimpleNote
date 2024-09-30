package com.bajapuik.simple_note.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val corotutinesDispatcherModule = module {
    single(
        qualifier = named(Dispatcher.Default)
    ) {
        Dispatchers.Default
    }

    single(
        qualifier = named(Dispatcher.IO)
    ) {
        Dispatchers.IO
    }

    single(
        qualifier = named(Dispatcher.Main)
    ) {
        Dispatchers.Main
    }
}

enum class Dispatcher {
    Default,
    IO,
    Main
}
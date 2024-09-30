package com.bajapuik.simple_note

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.bajapuik.simple_note.base.CoreApplication

fun main() {
    CoreApplication.initialize()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "SimpleNote",
        ) {
            App()
        }
    }
}
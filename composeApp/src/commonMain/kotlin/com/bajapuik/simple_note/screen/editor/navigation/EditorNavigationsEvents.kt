package com.bajapuik.simple_note.screen.editor.navigation

typealias OnEditorNavigationsEvents = (EditorNavigationsEvents) -> Unit

sealed class EditorNavigationsEvents {
    data object OnSuccessNavigation : EditorNavigationsEvents()
    data object OnBackNavigation : EditorNavigationsEvents()
}
package com.bajapuik.simple_note.screen.editor.events

typealias OnEditorEvents = (EditorEvents) -> Unit

sealed class EditorEvents {
    data class OnTitleInputChange(val value: String) : EditorEvents()
    data class OnContentInputChange(val value: String) : EditorEvents()
    data class OnSaveNoteClick(val id: Int?) : EditorEvents()
    data class OnDeleteNoteClick(val id: Int) : EditorEvents()
    data object OnBackClick : EditorEvents()
}
package com.bajapuik.simple_note.screen.editor.navigation

import kotlinx.serialization.Serializable

@Serializable
data class EditorRoute(
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null
)
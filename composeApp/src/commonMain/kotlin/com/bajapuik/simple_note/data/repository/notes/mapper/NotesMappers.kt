package com.bajapuik.simple_note.data.repository.notes.mapper

import com.bajapuik.simple_note.core.database.entity.NotesEntity
import com.bajapuik.simple_note.domain.model.Notes

internal fun List<NotesEntity>.asNotesDomainModel(): List<Notes> {
    return this.map {
        Notes(
            id = it.id,
            title = it.title,
            content = it.content,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}
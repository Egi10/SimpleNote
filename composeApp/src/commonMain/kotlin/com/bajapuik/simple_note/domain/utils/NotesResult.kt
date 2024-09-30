package com.bajapuik.simple_note.domain.utils

sealed class NotesResult<out T> {
    data class Success<out T>(val data: T): NotesResult<T>()
    data class Error(val exception: Throwable): NotesResult<Nothing>()
}
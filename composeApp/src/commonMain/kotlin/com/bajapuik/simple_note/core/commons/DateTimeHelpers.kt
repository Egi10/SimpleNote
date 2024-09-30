package com.bajapuik.simple_note.core.commons

import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

object DateTimeHelpers {
    @OptIn(FormatStringsInDatetimeFormats::class)
    fun getCurrentDateTime(
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val instantNow = Clock.System.now()
        val dateTime = instantNow.toLocalDateTime(
            timeZone = timeZone
        )
        val formatDate = LocalDateTime.Format {
            byUnicodePattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }
        return dateTime.format(
            format = formatDate
        )
    }
}
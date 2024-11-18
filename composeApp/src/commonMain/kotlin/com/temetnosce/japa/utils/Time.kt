package com.temetnosce.japa.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun currentTimestamp() = Clock.System.now().toEpochMilliseconds()
fun today() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
fun Long.toLocalDate(): LocalDate = Instant.fromEpochMilliseconds(this)
    .toLocalDateTime(TimeZone.currentSystemDefault()).date

fun LocalDate.toFormattedString(): String {
    return "${this.dayOfMonth.toString().padStart(2, '0')}.${
        this.monthNumber.toString().padStart(2, '0')
    }.${this.year}"
}

fun startOfTodayTimestamp(): Long {
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val startOfDay = currentDateTime.date.atStartOfDayIn(TimeZone.currentSystemDefault())
    return startOfDay.toEpochMilliseconds()
}

/**
 * Returns the timestamp of the start of the specified day (midnight), in milliseconds since the epoch.
 *
 * @param timeZone The time zone. The default is the system time zone.
 * @param date The date for which to get the start of the day.
 * @return The timestamp of the start of the day, in milliseconds.
 */
fun startOfDayTimestamp(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    date: LocalDate = Clock.System.now().toLocalDateTime(timeZone).date
): Long {
    val instant: Instant = date.atStartOfDayIn(timeZone)
    return instant.toEpochMilliseconds()
}

fun Long.formatNoHours(): String {
    val timeSec = this / 1000
    return when {
        timeSec / 60 < 10 && timeSec % 60 < 10 -> "0${timeSec / 60}:0${timeSec % 60}"
        timeSec / 60 < 10 && timeSec % 60 > 9 -> "0${timeSec / 60}:${timeSec % 60}"
        timeSec / 60 > 9 && timeSec % 60 < 10 -> "${timeSec / 60}:0${timeSec % 60}"
        else -> "${timeSec / 60}:${timeSec % 60}"
    }
}

fun Long.formatWithHours(): String {
    val timeSec = this / 1000
    val hours = timeSec / 3600
    val minutes = (timeSec % 3600) / 60
    val seconds = timeSec % 60

    return "${
        hours.toString().padStart(2, '0')
    }:${
        minutes.toString().padStart(2, '0')
    }:${
        seconds.toString().padStart(2, '0')
    }"
}

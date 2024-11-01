package com.temetnosce.japa.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun currentTimestamp() = Clock.System.now().toEpochMilliseconds()

fun startOfTodayTimestamp(): Long {
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val startOfDay = currentDateTime.date.atStartOfDayIn(TimeZone.currentSystemDefault())
    return startOfDay.toEpochMilliseconds()
}

/**
 * Returns the timestamp of the start of the specified day (midnight), in milliseconds since the epoch.
 *
 * @param date The date for which to get the start of the day.
 * @param timeZone The time zone. The default is the system time zone.
 * @return The timestamp of the start of the day, in milliseconds.
 */
fun startOfDayTimestamp(
    date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Long {
    val instant: Instant = date.atStartOfDayIn(timeZone)
    return instant.toEpochMilliseconds()
}
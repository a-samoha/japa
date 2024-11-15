package com.temetnosce.japa.data.dto

/**
 * @param startTimestamp is a PrimaryKay in the Local db
 */
data class ChantedRoundDto(
    val startTimestamp: Long = 0L,
    val endTimestamp: Long = 0L,
    val points: Byte = 0,
)
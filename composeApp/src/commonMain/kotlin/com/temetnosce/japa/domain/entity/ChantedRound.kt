package com.temetnosce.japa.domain.entity

data class ChantedRound(
    val index: Int = 1,
    val startTimestamp: Long = 0L,
    val endTimestamp: Long = 0L,
    val duration: String = "00:00",
    val points: Byte = 0,
)
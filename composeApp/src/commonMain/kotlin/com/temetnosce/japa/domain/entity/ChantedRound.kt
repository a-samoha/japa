package com.temetnosce.japa.domain.entity

data class ChantedRound(
    val index: Int = 1,
    val duration: String = "",
    val points: Byte = 0,
    val startTimestamp: Long = 0L,
    val endTimestamp: Long = 0L,
)
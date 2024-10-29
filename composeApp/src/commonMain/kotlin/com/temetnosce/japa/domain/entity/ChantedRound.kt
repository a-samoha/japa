package com.temetnosce.japa.domain.entity

data class ChantedRound(
    val index: Int = 1,
    val duration: String = "",
    val points: Int = 0,
    val startTimestamp: Long = 0L,
    val endTimestamp: Long = 0L,
)

fun chantedRounds(): List<ChantedRound> =
    listOf(
        ChantedRound(1, "07:24", 1),
        ChantedRound(2, "08:14", 5),
        ChantedRound(3, "07:04", 8),
        ChantedRound(4, "06:59", 10),
        ChantedRound(5, "07:59", 6),
        ChantedRound(6, "07:09", 7),
        ChantedRound(7, "07:49", 10),
        ChantedRound(8, "07:19", 7),
        ChantedRound(9, "07:35", 7),
        ChantedRound(10, "07:41", 3),
        ChantedRound(11, "07:29", 2),
        ChantedRound(12, "07:42", 5),
        ChantedRound(13, "07:33", 7),
        ChantedRound(14, "07:26", 9),
        ChantedRound(15, "07:48", 4),
        ChantedRound(16, "07:37", 7),
    )
package presentation.screen.home

data class ChantedRound(
    val index: Int,
    val time: String,
    val points: Int,
    val start: String = "00:00",
    val end: String = "00:00",
)

fun chantedRounds(): List<ChantedRound> =
    listOf(
        ChantedRound(1, "07:24", 5),
        ChantedRound(2, "08:14", 10),
        ChantedRound(3, "07:04", 8),
        ChantedRound(4, "06:59", 7),
        ChantedRound(5, "07:59", 6),
        ChantedRound(6, "07:09", 7),
    )
package com.temetnosce.japa.data.sources.localdb.rounds

import app.cash.sqldelight.coroutines.asFlow
import com.temetnosce.japa.data.dto.ChantedRoundDto
import com.temetnosce.japa.db.LocalDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

suspend fun LocalDb.getRoundByStartTimestamp(startTimestamp: Long): Result<ChantedRoundDto> =
    withContext(Dispatchers.IO) {
        runCatching {
            val cr = chantedRoundQueries.selectByTimestamp(startTimestamp).executeAsOneOrNull()
            ChantedRoundDto(
                startTimestamp = cr?.startTimestamp ?: 0L,
                endTimestamp = cr?.endTimestamp ?: 0L,
                points = cr?.points?.toByte() ?: 0,
            )
        }
    }

fun LocalDb.observeRoundsByDay(dayStartTimestamp: Long): Flow<List<ChantedRoundDto>> {
    val dayEndTimestamp = dayStartTimestamp + (24 * 60 * 60 * 1000)

    return chantedRoundQueries.selectByDay(dayStartTimestamp, dayEndTimestamp)
        .asFlow()
        .map { query ->
            query.executeAsList().map {
                ChantedRoundDto(
                    startTimestamp = it.startTimestamp,
                    endTimestamp = it.endTimestamp,
                    points = it.points.toByte(),
                )
            }
        }
}

suspend fun LocalDb.getRoundsByDay(dayStartTimestamp: Long): Result<List<ChantedRoundDto>> =
    withContext(Dispatchers.IO) {
        runCatching {
            val dayEndTimestamp = dayStartTimestamp + (24 * 60 * 60 * 1000)

            chantedRoundQueries.selectByDay(dayStartTimestamp, dayEndTimestamp)
                .executeAsList().map {
                    ChantedRoundDto(
                        startTimestamp = it.startTimestamp,
                        endTimestamp = it.endTimestamp,
                        points = it.points.toByte(),
                    )
                }
        }
    }

suspend fun LocalDb.insertOrReplaceRound(
    startTimestamp: Long,
    endTimestamp: Long,
    points: Byte,
): Result<Unit> = withContext(Dispatchers.IO) {
    runCatching {
        chantedRoundQueries.insertOrReplace(
            startTimestamp = startTimestamp,
            endTimestamp = endTimestamp,
            points = points.toLong(),
        )
    }
}

suspend fun LocalDb.updateByTimestamp(
    startTimestamp: Long,
    endTimestamp: Long,
    points: Byte,
): Result<Unit> = withContext(Dispatchers.IO) {
    runCatching {
        chantedRoundQueries.updateByTimestamp(
            startTimestamp = startTimestamp,
            endTimestamp = endTimestamp,
            points = points.toLong(),
        )
    }
}

suspend fun LocalDb.deleteByTimestamp(
    startTimestamp: Long,
): Result<Unit> = withContext(Dispatchers.IO) {
    runCatching {
        chantedRoundQueries.deleteByTimestamp(startTimestamp = startTimestamp)
    }
}
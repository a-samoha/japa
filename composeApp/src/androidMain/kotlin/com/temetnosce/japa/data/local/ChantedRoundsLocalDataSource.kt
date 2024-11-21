package com.temetnosce.japa.data.local

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.temetnosce.japa.data.dto.ChantedRoundDto
import com.temetnosce.japa.data.sources.localdb.rounds.deleteByTimestamp
import com.temetnosce.japa.data.sources.localdb.rounds.getRoundByStartTimestamp
import com.temetnosce.japa.data.sources.localdb.rounds.getRoundsByDay
import com.temetnosce.japa.data.sources.localdb.rounds.insertOrReplaceRound
import com.temetnosce.japa.data.sources.localdb.rounds.observeRoundsByDay
import com.temetnosce.japa.data.sources.localdb.rounds.updateByTimestamp
import com.temetnosce.japa.db.LocalDb
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import kotlinx.coroutines.flow.Flow

class ChantedRoundsLocalDataSource(
    private val sqlDriver: AndroidSqliteDriver
) : ChantedRoundsDataSource.Local {

    private val localDb by lazy {
        LocalDb(sqlDriver)
    }

    override fun observeRoundsByDay(dayStartTimestamp: Long): Flow<List<ChantedRoundDto>> =
        localDb.observeRoundsByDay(dayStartTimestamp)

    override suspend fun getRoundsByDay(dayStartTimestamp: Long): Result<List<ChantedRoundDto>> =
        localDb.getRoundsByDay(dayStartTimestamp)

    override suspend fun get(startTimestamp: Long): Result<ChantedRoundDto> =
        localDb.getRoundByStartTimestamp(startTimestamp)

    override suspend fun save(round: ChantedRoundDto) =
        localDb.insertOrReplaceRound(
            startTimestamp = round.startTimestamp,
            endTimestamp = round.endTimestamp,
            points = round.points,
        )

    override suspend fun update(round: ChantedRoundDto): Result<Unit> =
        localDb.updateByTimestamp(
            startTimestamp = round.startTimestamp,
            endTimestamp = round.endTimestamp,
            points = round.points,
        )

    override suspend fun delete(startTimestamp: Long): Result<Unit> =
        localDb.deleteByTimestamp(startTimestamp = startTimestamp)

}
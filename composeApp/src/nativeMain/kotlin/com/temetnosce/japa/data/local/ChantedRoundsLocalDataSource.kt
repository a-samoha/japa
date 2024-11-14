package com.temetnosce.japa.data.local

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.temetnosce.japa.data.dto.ChantedRoundDto
import com.temetnosce.japa.data.sources.localdb.rounds.getRoundByStartTimestamp
import com.temetnosce.japa.data.sources.localdb.rounds.getRoundsByDay
import com.temetnosce.japa.data.sources.localdb.rounds.insertOrReplaceRound
import com.temetnosce.japa.db.LocalDb
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import kotlinx.coroutines.flow.Flow

class ChantedRoundsLocalDataSource(
    private val sqlDriver: NativeSqliteDriver,
) : ChantedRoundsDataSource.Local {

    private val localDb by lazy {
        LocalDb(sqlDriver)
    }

    override fun observe(dayStartTimestamp: Long): Flow<List<ChantedRoundDto>> =
        localDb.getRoundsByDay(dayStartTimestamp)

    override suspend fun get(startedTimeStamp: Long): Result<ChantedRoundDto> =
        localDb.getRoundByStartTimestamp(startedTimeStamp)

    override suspend fun save(round: ChantedRoundDto) =
        localDb.insertOrReplaceRound(
            startTimestamp = round.startTimestamp,
            endTimestamp = round.endTimestamp,
            points = round.points
        )
}
package com.temetnosce.japa.data.repository

import com.temetnosce.japa.data.dto.ChantedRoundDto
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import com.temetnosce.japa.utils.formatNoHours
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChantedRoundsRepositoryImpl(
    private val localDataSource: ChantedRoundsDataSource.Local
) : ChantedRoundsRepository {

    override fun observe(dayStartTimestamp: Long): Flow<List<ChantedRound>> =
        localDataSource.observeRoundsByDay(dayStartTimestamp).map {
            it.mapIndexed { index, round ->
                ChantedRound(
                    index = index + 1,
                    duration = (round.endTimestamp - round.startTimestamp).formatNoHours(),
                    points = round.points,
                    startTimestamp = round.startTimestamp,
                    endTimestamp = round.endTimestamp,
                )
            }
        }

    override suspend fun getByTimestamp(startTimestamp: Long): Result<ChantedRound> =
        localDataSource.get(startTimestamp).map {
            ChantedRound(
                startTimestamp = it.startTimestamp,
                endTimestamp = it.endTimestamp,
                duration = (it.endTimestamp - it.startTimestamp).formatNoHours(),
                points = it.points,
            )
        }

    override suspend fun save(round: ChantedRound): Result<Unit> =
        localDataSource.save(
            ChantedRoundDto(
                startTimestamp = round.startTimestamp,
                endTimestamp = round.endTimestamp,
                points = round.points,
            )
        )

    override suspend fun update(round: ChantedRound): Result<Unit> =
        localDataSource.update(
            ChantedRoundDto(
                startTimestamp = round.startTimestamp,
                endTimestamp = round.endTimestamp,
                points = round.points,
            )
        )

}
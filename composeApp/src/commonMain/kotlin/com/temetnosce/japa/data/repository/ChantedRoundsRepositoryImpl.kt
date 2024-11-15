package com.temetnosce.japa.data.repository

import com.temetnosce.japa.data.dto.ChantedRoundDto
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import com.temetnosce.japa.presentation.screen.home.components.formatNoHours
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChantedRoundsRepositoryImpl(
    private val localDataSource: ChantedRoundsDataSource.Local
) : ChantedRoundsRepository {

    override fun observe(dayStartTimestamp: Long): Flow<List<ChantedRound>> =
        localDataSource.observe(dayStartTimestamp).map {
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

    override suspend fun getByTimestamp(): Result<ChantedRound> {
        TODO("Not yet implemented")
    }

    override suspend fun save(round: ChantedRound): Result<Unit> =
        localDataSource.save(
            ChantedRoundDto(
                startTimestamp = round.startTimestamp,
                endTimestamp = round.endTimestamp,
                points = round.points,
            )
        )
}
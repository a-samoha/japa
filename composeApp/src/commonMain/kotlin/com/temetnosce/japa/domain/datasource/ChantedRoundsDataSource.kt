package com.temetnosce.japa.domain.datasource

import com.temetnosce.japa.data.dto.ChantedRoundDto
import kotlinx.coroutines.flow.Flow

sealed interface ChantedRoundsDataSource {

    interface Local : ChantedRoundsDataSource {
        fun observeRoundsByDay(dayStartTimestamp: Long): Flow<List<ChantedRoundDto>>
        suspend fun getRoundsByDay(dayStartTimestamp: Long): Result<List<ChantedRoundDto>>
        suspend fun get(startTimestamp: Long): Result<ChantedRoundDto>
        suspend fun save(round: ChantedRoundDto): Result<Unit>
        suspend fun update(round: ChantedRoundDto): Result<Unit>
        suspend fun delete(startTimestamp: Long): Result<Unit>
    }

    interface Remote : ChantedRoundsDataSource
}
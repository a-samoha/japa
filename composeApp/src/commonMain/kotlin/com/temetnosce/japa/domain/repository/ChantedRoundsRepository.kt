package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ChantedRoundsRepository {
    fun observe(dayStartTimestamp: Long): Flow<List<ChantedRound>>
    suspend fun getByTimestamp(startTimestamp: Long): Result<ChantedRound>
    suspend fun save(round: ChantedRound): Result<Unit>
    suspend fun update(round: ChantedRound): Result<Unit>
    suspend fun delete(startTimestamp: Long): Result<Unit>
}

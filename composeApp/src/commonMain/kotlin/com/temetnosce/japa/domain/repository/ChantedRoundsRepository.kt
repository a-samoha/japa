package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ChantedRoundsRepository {
    fun observe(dayStartTimestamp: Long): Flow<List<ChantedRound>>
    suspend fun save(round: ChantedRound): Result<Unit>
}

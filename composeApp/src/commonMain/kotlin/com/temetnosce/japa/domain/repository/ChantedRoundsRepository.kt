package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ChantedRoundsRepository {
    fun observe(): Flow<List<ChantedRound>>
    fun save(chantedRound: ChantedRound)
    fun helloWorld(): String
}

package com.temetnosce.japa.domain.usecase.home

import com.temetnosce.japa.domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ObserveChantedRoundsUseCase {
    operator fun invoke(dayStartTimestamp: Long): Flow<List<ChantedRound>>
}
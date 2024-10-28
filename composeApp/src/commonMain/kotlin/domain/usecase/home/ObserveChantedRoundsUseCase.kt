package domain.usecase.home

import domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ObserveChantedRoundsUseCase {
    operator fun invoke(): Flow<List<ChantedRound>>
}
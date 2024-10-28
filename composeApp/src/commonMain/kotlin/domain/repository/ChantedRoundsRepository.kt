package domain.repository

import domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow

interface ChantedRoundsRepository {
    fun observe(): Flow<List<ChantedRound>>
    fun save(chantedRound: ChantedRound)
    fun helloWorld(): String
}

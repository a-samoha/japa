package data.repository

import data.local.ChantedRoundsLocalDataSource
import domain.entity.ChantedRound
import domain.repository.ChantedRoundsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ChantedRoundsRepositoryImpl(
    private val chantedRoundsLocalDataSource: ChantedRoundsLocalDataSource
) : ChantedRoundsRepository {

    private val _chantedRoundsFlow = MutableStateFlow<List<ChantedRound>>(emptyList())

    override fun observe(): Flow<List<ChantedRound>> = _chantedRoundsFlow.asStateFlow()

    override fun save(chantedRound: ChantedRound) {
        _chantedRoundsFlow.update { it + chantedRound }
    }

    override fun helloWorld(): String {
        return "Hello World!"
    }
}
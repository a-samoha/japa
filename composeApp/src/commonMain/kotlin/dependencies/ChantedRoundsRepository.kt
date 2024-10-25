package dependencies

import domain.entity.ChantedRound
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ChantedRoundsRepository {
    fun observe(): Flow<List<ChantedRound>>
    fun save(chantedRound: ChantedRound)
    fun helloWorld(): String
}

class ChantedRoundsRepositoryImpl(
    private val dbClient: DbClient
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
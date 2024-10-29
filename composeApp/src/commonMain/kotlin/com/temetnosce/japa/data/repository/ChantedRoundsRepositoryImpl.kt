package com.temetnosce.japa.data.repository

import com.temetnosce.japa.data.local.ChantedRoundsLocalDataSource
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
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
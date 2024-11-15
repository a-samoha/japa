package com.temetnosce.japa.presentation.screen.emend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmendViewModel(
    private val chantedRoundsRepo: ChantedRoundsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ChantedRound())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ChantedRound(),
        )

    private fun loadData() {
        viewModelScope.launch {
            chantedRoundsRepo.getByTimestamp()
                .onFailure { println("Error: $it") }
                .onSuccess { _state.update { it } }
        }
    }

}
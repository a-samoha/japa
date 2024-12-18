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
    private val startTimestamp: Long,
    private val chantedRoundsRepo: ChantedRoundsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(EmendState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = EmendState(),
        )

    private fun loadData() {
        viewModelScope.launch {
            chantedRoundsRepo.getByTimestamp(startTimestamp)
                .onFailure { println("Error: $it") } //todo show error msg
                .onSuccess { round -> _state.update { it.copy(chantedRound = round) } }
        }
    }

    fun onRoundUpdate(updatedRound: ChantedRound) {
        _state.update { it.copy(chantedRound = updatedRound) }
    }

    fun onAccept() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            chantedRoundsRepo.update(state.value.chantedRound)
                .onFailure { println("Error: $it") } //todo show error msg
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isUpdatedSuccessfully = true
                        )
                    }
                }
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            chantedRoundsRepo.delete(state.value.chantedRound.startTimestamp)
                .onFailure { println("Error: $it") } //todo show error msg
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isUpdatedSuccessfully = true
                        )
                    }
                }
        }
    }
}
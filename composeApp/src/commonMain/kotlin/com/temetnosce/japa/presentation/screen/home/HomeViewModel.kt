package com.temetnosce.japa.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import com.temetnosce.japa.domain.repository.ShlokasRepository
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState
import com.temetnosce.japa.utils.startOfDayTimestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val chantedRoundsRepo: ChantedRoundsRepository,
    private val shlokasRepo: ShlokasRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HomeState()
        )

    private fun loadData() {
        chantedRoundsRepo.observe(startOfDayTimestamp())
            .map { chantedRounds ->
                val shloka = shlokasRepo.getShloka(chantedRounds.size)
                chantedRounds to shloka
            }
            .onEach { pair ->
                _state.update {
                    it.copy(
                        chantedRounds = pair.first,
                        shloka = pair.second
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun addChantedRound(chantedRound: ChantedRound) {
        viewModelScope.launch {
            chantedRoundsRepo.save(chantedRound)
                .onFailure { println("Saving failure") }
                .onSuccess { println("Saving success") }
        }
    }

    fun showJapaPointsDialog(isVisible: Boolean) {
        _state.update { it.copy(isPointsDialogVisible = isVisible) }
    }

    fun setStopwatchState(stopwatchState: StopWatchState) {
        _state.update { it.copy(stopWatchState = stopwatchState) }
    }
}

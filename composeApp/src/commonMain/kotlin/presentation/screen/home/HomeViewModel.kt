package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dependencies.ChantedRoundsRepository
import domain.entity.ChantedRound
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import presentation.screen.home.components.StopWatchState

class HomeViewModel(
    private val repo: ChantedRoundsRepository
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
        repo.observe()
            .onEach { chantedRounds -> _state.update { it.copy(chantedRounds = chantedRounds) } }
            .launchIn(viewModelScope)
    }

    fun addChantedRound(chantedRound: ChantedRound) {
        repo.save(chantedRound)
    }

    fun showJapaPointsDialog(isVisible: Boolean) {
        _state.update { it.copy(isPointsDialogVisible = isVisible) }
    }

    fun setStopwatchState(stopwatchState: StopWatchState) {
        _state.update { it.copy(stopWatchState = stopwatchState) }
    }
}

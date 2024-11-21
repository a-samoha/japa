package com.temetnosce.japa.presentation.screen.home

import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import com.temetnosce.japa.domain.repository.SettingsRepository
import com.temetnosce.japa.domain.repository.ShlokasRepository
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState
import com.temetnosce.japa.utils.formatNoHours
import com.temetnosce.japa.utils.formatWithHours
import com.temetnosce.japa.utils.startOfDayTimestamp
import com.temetnosce.japa.utils.toLocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class HomeViewModel(
    private val settingsRepo: SettingsRepository,
    private val chantedRoundsRepo: ChantedRoundsRepository,
    private val shlokasRepo: ShlokasRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart { loadData(startOfDayTimestamp()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HomeState()
        )

    private fun loadData(startOfDayTimestamp: Long) {
        chantedRoundsRepo.observe(startOfDayTimestamp)
            .map { chantedRounds ->
                val shloka = shlokasRepo.getShloka(
                    when {
                        chantedRounds.size < 16 -> chantedRounds.size
                        chantedRounds.size in 16..31 -> chantedRounds.size - 16
                        chantedRounds.size in 32..47 -> chantedRounds.size - 32
                        chantedRounds.size in 48..63 -> chantedRounds.size - 48
                        chantedRounds.size in 64..79 -> chantedRounds.size - 64
                        else -> 0
                    }
                )
                chantedRounds to shloka
            }
            .onEach { pair ->
                _state.update {

                    var totalJapaTIme = 0L
                    pair.first.fastForEach { cr ->
                        totalJapaTIme += (cr.endTimestamp - cr.startTimestamp)
                    }

                    var bestRound = 0L
                    pair.first.fastForEach { cr ->
                        val roundTimeSec = (cr.endTimestamp - cr.startTimestamp)
                        when {
                            bestRound == 0L -> {
                                bestRound = roundTimeSec
                            }
                            bestRound != 0L && roundTimeSec < bestRound -> {
                                bestRound = roundTimeSec
                            }
                            else -> Unit
                        }
                    }

                    it.copy(
                        renderedDate = startOfDayTimestamp.toLocalDate(),
                        chantedRounds = pair.first,
                        totalTime = totalJapaTIme.formatWithHours(),
                        bestRound = bestRound.formatNoHours(),
                        shloka = pair.second,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun addChantedRound(chantedRound: ChantedRound) {
        viewModelScope.launch {
            chantedRoundsRepo.save(chantedRound)
                .onFailure { println("Saving error: $it") } // todo show error msg
        }
    }

    fun showJapaPointsDialog(isVisible: Boolean) {
        _state.update { it.copy(isPointsDialogVisible = isVisible) }
    }

    fun setStopwatchState(stopwatchState: StopWatchState) {
        _state.update { it.copy(stopWatchState = stopwatchState) }
    }

    fun onChartSwipeRight() =
        loadData(startOfDayTimestamp(date = state.value.renderedDate.minus(1, DateTimeUnit.DAY)))

    fun onChartSwipeLeft() =
        loadData(startOfDayTimestamp(date = state.value.renderedDate.plus(1, DateTimeUnit.DAY)))

}

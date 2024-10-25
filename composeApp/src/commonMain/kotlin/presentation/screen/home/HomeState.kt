package presentation.screen.home

import androidx.compose.runtime.Immutable
import domain.entity.ChantedRound
import domain.entity.Shloka
import presentation.screen.home.components.StopWatchState

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val chantedRounds: List<ChantedRound> = emptyList(),
    val stopWatchState: StopWatchState = StopWatchState.DEFAULT,
    val isPointsDialogVisible: Boolean = false,
    val shloka: Shloka = Shloka(),
)
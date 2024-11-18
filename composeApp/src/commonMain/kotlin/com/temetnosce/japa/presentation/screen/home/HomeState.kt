package com.temetnosce.japa.presentation.screen.home

import androidx.compose.runtime.Immutable
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.entity.Shloka
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState
import com.temetnosce.japa.utils.today
import kotlinx.datetime.LocalDate

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val renderedDate: LocalDate = today(),
    val chantedRounds: List<ChantedRound> = emptyList(),
    val totalTime: String = "",
    val bestRound: String = "",
    val stopWatchState: StopWatchState = StopWatchState.DEFAULT,
    val isPointsDialogVisible: Boolean = false,
    val shloka: Shloka = Shloka(),
)
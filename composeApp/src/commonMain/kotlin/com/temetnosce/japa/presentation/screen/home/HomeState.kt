package com.temetnosce.japa.presentation.screen.home

import androidx.compose.runtime.Immutable
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.domain.entity.Shloka
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val chantedRounds: List<ChantedRound> = emptyList(),
    val stopWatchState: StopWatchState = StopWatchState.DEFAULT,
    val isPointsDialogVisible: Boolean = false,
    val shloka: Shloka = Shloka(),
)
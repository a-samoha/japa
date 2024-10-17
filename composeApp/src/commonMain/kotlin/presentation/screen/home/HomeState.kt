package presentation.screen.home

import domain.entity.Shloka

data class HomeState(
    val isLoading: Boolean = false,
    val shloka: Shloka
)
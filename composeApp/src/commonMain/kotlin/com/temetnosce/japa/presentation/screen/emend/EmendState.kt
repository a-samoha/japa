package com.temetnosce.japa.presentation.screen.emend

import com.temetnosce.japa.domain.entity.ChantedRound


data class EmendState(
    val isLoading: Boolean = false,
    val chantedRound: ChantedRound = ChantedRound(),
    val isUpdatedSuccessfully: Boolean? = null,
)
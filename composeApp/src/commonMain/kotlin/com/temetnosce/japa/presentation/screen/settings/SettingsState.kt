package com.temetnosce.japa.presentation.screen.settings

import com.temetnosce.japa.domain.entity.Settings

data class SettingsState(
    val isLoading: Boolean = true,
    val settings: Settings = Settings(),
)

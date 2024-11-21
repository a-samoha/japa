package com.temetnosce.japa.presentation.screen.settings

import com.temetnosce.japa.domain.entity.Language

sealed interface SettingsIntent {

    data class UserLanguage(val language: Language) : SettingsIntent

    sealed interface UpdateFlag : SettingsIntent {

        val flag: Boolean

        data class SystemDarkTheme(override val flag: Boolean) : UpdateFlag

        data class DarkTheme(override val flag: Boolean) : UpdateFlag
    }
}
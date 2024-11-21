package com.temetnosce.japa.domain.entity

import androidx.compose.runtime.Immutable

data class Settings(
    val language: Language = Language.EN,
    val designUseSystemColorPalette: Boolean = false,
    val designUseSystemDarkTheme: Boolean = false,
    val designDarkTheme: Boolean = false,
    val developerMode: Boolean = false,
)

@Immutable
enum class Language {
    EN, UA, RU;

    companion object {
        fun fromString(raw: String): Language {
            return entries.find { it.name.equals(raw, ignoreCase = true) } ?: EN
        }
    }
}
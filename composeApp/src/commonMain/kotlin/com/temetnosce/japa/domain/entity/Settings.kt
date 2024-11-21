package com.temetnosce.japa.domain.entity

data class Settings(
    val language: Language = Language.English,
    val designUseSystemColorPalette: Boolean = false,
    val designUseSystemDarkTheme: Boolean = false,
    val designDarkTheme: Boolean = false,
    val developerMode: Boolean = false,
)

sealed class Language(val isoFormat: String, val uiName: String) {

    data object English : Language("en", "eng")

    data object Ukrainian : Language("uk", "ukr")

    data object Russian : Language("ru", "rus")

    companion object {
        fun fromString(raw: String): Language {
            return when (raw) {
                "en" -> English
                "uk" -> Ukrainian
                "ru" -> Russian
                else -> English
            }
        }
    }
}

enum class SettingsKeys {
    settings,
    language,
    designUseSystemColorPalette,
    designUseSystemDarkTheme,
    designDarkTheme,
    developerMode;
}
package com.temetnosce.japa.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.entity.Language
import com.temetnosce.japa.domain.entity.Settings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.settingsDataStore by preferencesDataStore(name = "settings")

class SettingsDataSourceImpl(
    private val context: Context,
) : SettingsDataSource {
    override fun load(): Settings {
        val preferences = runBlocking { context.settingsDataStore.data.first() }
        return Settings(
            language =
            Language.fromString(preferences[stringPreferencesKey("language")] ?: "en"),
            designUseSystemColorPalette =
            preferences[booleanPreferencesKey("designUseSystemColorPalette")] ?: false,
            designUseSystemDarkTheme =
            preferences[booleanPreferencesKey("designUseSystemDarkTheme")] ?: false,
            designDarkTheme =
            preferences[booleanPreferencesKey("designDarkTheme")] ?: false,
            developerMode =
            preferences[booleanPreferencesKey("developerMode")] ?: false,
        )
    }

    override fun save(settings: Settings) {
        runBlocking {
            context.settingsDataStore.edit { preferences ->
                preferences[stringPreferencesKey("language")] =
                    settings.language.name.lowercase()
                preferences[booleanPreferencesKey("designUseSystemColorPalette")] =
                    settings.designUseSystemColorPalette
                preferences[booleanPreferencesKey("designUseSystemDarkTheme")] =
                    settings.designUseSystemDarkTheme
                preferences[booleanPreferencesKey("designDarkTheme")] =
                    settings.designDarkTheme
                preferences[booleanPreferencesKey("developerMode")] =
                    settings.developerMode
            }
        }
    }
}
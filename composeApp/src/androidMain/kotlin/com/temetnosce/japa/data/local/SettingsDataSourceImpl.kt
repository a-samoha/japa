package com.temetnosce.japa.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.entity.Language
import com.temetnosce.japa.domain.entity.Language.English
import com.temetnosce.japa.domain.entity.Settings
import com.temetnosce.japa.domain.entity.SettingsKeys.designDarkTheme
import com.temetnosce.japa.domain.entity.SettingsKeys.designUseSystemColorPalette
import com.temetnosce.japa.domain.entity.SettingsKeys.designUseSystemDarkTheme
import com.temetnosce.japa.domain.entity.SettingsKeys.developerMode
import com.temetnosce.japa.domain.entity.SettingsKeys.language
import com.temetnosce.japa.domain.entity.SettingsKeys.settings
import kotlinx.coroutines.flow.first

private val Context.settingsDataStore by preferencesDataStore(name = settings.name)

class SettingsDataSourceImpl(
    private val context: Context,
) : SettingsDataSource {

    override suspend fun load(): Settings {
        val preferences = context.settingsDataStore.data.first()
        return Settings(
            language =
            Language.fromString(preferences[stringPreferencesKey(language.name)] ?: English.isoFormat),
            designUseSystemColorPalette =
            preferences[booleanPreferencesKey(designUseSystemColorPalette.name)] ?: false,
            designUseSystemDarkTheme =
            preferences[booleanPreferencesKey(designUseSystemDarkTheme.name)] ?: false,
            designDarkTheme =
            preferences[booleanPreferencesKey(designDarkTheme.name)] ?: false,
            developerMode =
            preferences[booleanPreferencesKey(developerMode.name)] ?: false,
        )
    }

    override suspend fun edit(settings: Settings): Result<Unit> =
        runCatching {
            context.settingsDataStore.edit { preferences ->
                preferences[stringPreferencesKey(language.name)] =
                    settings.language.isoFormat
                preferences[booleanPreferencesKey(designUseSystemColorPalette.name)] =
                    settings.designUseSystemColorPalette
                preferences[booleanPreferencesKey(designUseSystemDarkTheme.name)] =
                    settings.designUseSystemDarkTheme
                preferences[booleanPreferencesKey(designDarkTheme.name)] =
                    settings.designDarkTheme
                preferences[booleanPreferencesKey(developerMode.name)] =
                    settings.developerMode
            }
        }
}

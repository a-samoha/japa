package com.temetnosce.japa.data.local

import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.entity.Language
import com.temetnosce.japa.domain.entity.Language.English
import com.temetnosce.japa.domain.entity.Settings
import com.temetnosce.japa.domain.entity.SettingsKeys.*
import platform.Foundation.NSUserDefaults

class SettingsDataSourceImpl() : SettingsDataSource {

    override suspend fun load(): Settings {
        val defaults = NSUserDefaults.standardUserDefaults
        return Settings(
            language = Language.fromString(defaults.stringForKey(language.name)?: English.isoFormat),
            designUseSystemColorPalette = defaults.boolForKey(designUseSystemColorPalette.name),
            designUseSystemDarkTheme = defaults.boolForKey(designUseSystemDarkTheme.name),
            designDarkTheme = defaults.boolForKey(designDarkTheme.name),
            developerMode = defaults.boolForKey(developerMode.name),
        )
    }

    override suspend fun edit(settings: Settings): Result<Unit> =
        kotlin.runCatching {
            val defaults = NSUserDefaults.standardUserDefaults
            defaults.setObject(settings.language.isoFormat, forKey = language.name)
            defaults.setBool(settings.designUseSystemColorPalette, forKey = designUseSystemColorPalette.name)
            defaults.setBool(settings.designUseSystemDarkTheme, forKey = designUseSystemDarkTheme.name)
            defaults.setBool(settings.designDarkTheme, forKey = designDarkTheme.name)
            defaults.setBool(settings.developerMode, forKey = developerMode.name)
        }
}
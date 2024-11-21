package com.temetnosce.japa.data.local

import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.entity.Settings
import platform.Foundation.NSUserDefaults

class SettingsDataSourceImpl() : SettingsDataSource {
    override fun load(): Settings {
        val defaults = NSUserDefaults.standardUserDefaults()
        return Settings(
            developerMode = defaults.boolForKey("developerMode"),
            designUseSystemColorPalette = defaults.boolForKey("designUseSystemColorPalette"),
            designUseSystemDarkTheme = defaults.boolForKey("designUseSystemDarkTheme"),
            designDarkTheme = defaults.boolForKey("designDarkTheme"),
        )
    }

    override fun save(settings: Settings) {
        val defaults = NSUserDefaults.standardUserDefaults()
        defaults.setBool(settings.developerMode, forKey = "developerMode")
        defaults.setBool(
            settings.designUseSystemColorPalette,
            forKey = "designUseSystemColorPalette"
        )
        defaults.setBool(settings.designUseSystemDarkTheme, forKey = "designUseSystemDarkTheme")
        defaults.setBool(settings.designDarkTheme, forKey = "designDarkTheme")
    }
}
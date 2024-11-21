package com.temetnosce.japa.domain.datasource

import com.temetnosce.japa.domain.entity.Settings

interface SettingsDataSource {
    fun load(): Settings
    fun save(settings: Settings)
}
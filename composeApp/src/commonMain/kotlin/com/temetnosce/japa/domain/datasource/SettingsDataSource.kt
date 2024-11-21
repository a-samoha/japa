package com.temetnosce.japa.domain.datasource

import com.temetnosce.japa.domain.entity.Settings

interface SettingsDataSource {
    suspend fun load(): Settings
    suspend fun edit(settings: Settings): Result<Unit>
}
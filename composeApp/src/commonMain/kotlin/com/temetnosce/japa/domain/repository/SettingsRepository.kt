package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun observe(): Flow<Settings>
    fun updateSettings(update: Settings): Result<Unit>

    fun getSettings(): Settings
}
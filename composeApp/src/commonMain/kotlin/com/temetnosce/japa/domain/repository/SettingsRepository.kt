package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun fetch()
    fun observe(): Flow<Settings>
    suspend fun updateSettings(update: Settings): Result<Unit>
    suspend fun getSettings(): Settings
}
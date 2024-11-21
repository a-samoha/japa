package com.temetnosce.japa.data.repository

import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.entity.Settings
import com.temetnosce.japa.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsRepositoryImpl(
    private val settingsDataSource: SettingsDataSource,
) : SettingsRepository {

    private val settingsFlow = MutableStateFlow(Settings())

    override suspend fun fetch() = settingsFlow.update { settingsDataSource.load() }

    override fun observe(): Flow<Settings> = settingsFlow.asStateFlow()

    override suspend fun getSettings(): Settings = settingsDataSource.load()

    override suspend fun updateSettings(update: Settings): Result<Unit> =
        runCatching {
            settingsDataSource.edit(update)
            fetch()
        }
}
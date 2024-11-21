package com.temetnosce.japa.data.repository

import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Language
import com.temetnosce.japa.domain.entity.Shloka
import com.temetnosce.japa.domain.repository.SettingsRepository
import com.temetnosce.japa.domain.repository.ShlokasRepository

class ShlokasRepositoryImpl(
    private val settingsRepo: SettingsRepository,
    private val shlokasLocalDataSource: ShlokasDataSource.Local,
) : ShlokasRepository {

    private val shlokasEn by lazy {
        shlokasLocalDataSource.loadShlokas()
    }

    private val shlokasUk by lazy {
        shlokasLocalDataSource.loadShlokas()
    }

    private val shlokasRu by lazy {
        shlokasLocalDataSource.loadShlokas()
    }

    override suspend fun getShloka(id: String): Shloka =
        when (settingsRepo.getSettings().language) {
            Language.English -> shlokasEn.find { it.id == id } ?: Shloka()
            Language.Ukrainian -> shlokasUk.find { it.id == id } ?: Shloka()
            Language.Russian -> shlokasRu.find { it.id == id } ?: Shloka()
        }


    override suspend fun getShloka(index: Int): Shloka {
        return try {
            when (settingsRepo.getSettings().language) {
                Language.English -> shlokasEn[index]
                Language.Ukrainian -> shlokasUk[index]
                Language.Russian -> shlokasRu[index]
            }
        } catch (e: Exception) {
            Shloka()
        }
    }
}
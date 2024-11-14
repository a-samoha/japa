package com.temetnosce.japa.data.repository

import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Shloka
import com.temetnosce.japa.domain.repository.ShlokasRepository

class ShlokasRepositoryImpl(
    private val shlokasLocalDataSource: ShlokasDataSource.Local,
) : ShlokasRepository {

    private val shlokas by lazy {
        shlokasLocalDataSource.loadShlokas()
    }

    override fun getShloka(id: String): Shloka =
        shlokas.find { it.id == id } ?: Shloka()

    override fun getShloka(index: Int): Shloka {
        return try {
            shlokas[index]
        } catch (e: Exception){
            Shloka()
        }
    }
}
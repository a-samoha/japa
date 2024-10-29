package com.temetnosce.japa.data.repository

import com.temetnosce.japa.data.local.ShlokasLocalDataSource
import com.temetnosce.japa.domain.entity.Shloka
import com.temetnosce.japa.domain.repository.ShlokasRepository

class ShlokasRepositoryImpl(
    private val shlokasLocalDataSource: ShlokasLocalDataSource,
) : ShlokasRepository {

    private val shlokas by lazy {
        shlokasLocalDataSource.loadShlokas()
    }

    override fun getShloka(id: String): Shloka =
        shlokas.find { it.id == id } ?: Shloka()

    override fun getShloka(index: Int): Shloka {
        return shlokas[index]
    }
}
package data.repository

import data.local.ShlokasLocalDataSource
import domain.entity.Shloka
import domain.repository.ShlokasRepository

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
package domain.repository

import domain.entity.Shloka

interface ShlokasRepository {
    fun getShloka(id: String): Shloka
    fun getShloka(index: Int): Shloka
}
package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.Shloka

interface ShlokasRepository {
    suspend fun getShloka(id: String): Shloka
    suspend fun getShloka(index: Int): Shloka
}
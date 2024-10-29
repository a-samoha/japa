package com.temetnosce.japa.domain.repository

import com.temetnosce.japa.domain.entity.Shloka

interface ShlokasRepository {
    fun getShloka(id: String): Shloka
    fun getShloka(index: Int): Shloka
}
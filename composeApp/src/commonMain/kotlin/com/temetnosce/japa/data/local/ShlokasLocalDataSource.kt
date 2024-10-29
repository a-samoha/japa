package com.temetnosce.japa.data.local

import com.temetnosce.japa.domain.entity.Shloka

expect class ShlokasLocalDataSource {
    fun loadShlokas(): List<Shloka>
}
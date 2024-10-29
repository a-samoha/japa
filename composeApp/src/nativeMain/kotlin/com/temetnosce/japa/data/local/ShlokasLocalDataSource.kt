package com.temetnosce.japa.data.local

import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Shloka

actual class ShlokasLocalDataSource : ShlokasDataSource.Local {
    actual override fun loadShlokas(): List<Shloka> {
        TODO("Not yet implemented")
    }
}
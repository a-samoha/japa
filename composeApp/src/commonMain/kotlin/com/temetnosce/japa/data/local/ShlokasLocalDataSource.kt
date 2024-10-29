package com.temetnosce.japa.data.local

import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Shloka

expect class ShlokasLocalDataSource : ShlokasDataSource.Local {
    override fun loadShlokas(): List<Shloka>
}
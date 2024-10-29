package com.temetnosce.japa.domain.datasource

import com.temetnosce.japa.domain.entity.Shloka

interface ShlokasDataSource {

    interface Local : ShlokasDataSource {
        fun loadShlokas(): List<Shloka>
    }

    interface Remote : ShlokasDataSource
}
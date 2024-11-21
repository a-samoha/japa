package com.temetnosce.japa.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.temetnosce.japa.IosLocalizationProvider
import com.temetnosce.japa.LocalizationProvider
import com.temetnosce.japa.data.local.ChantedRoundsLocalDataSource
import com.temetnosce.japa.data.local.ShlokasLocalDataSource
import com.temetnosce.japa.db.LocalDb
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {

    single {
        NativeSqliteDriver(
            schema = LocalDb.Schema,
            name = "chanted_rounds.db",
        )
    }

    singleOf(::IosLocalizationProvider) bind LocalizationProvider::class

    singleOf(::ChantedRoundsLocalDataSource) bind ChantedRoundsDataSource.Local::class

    factoryOf(::ShlokasLocalDataSource) bind ShlokasDataSource.Local::class
}
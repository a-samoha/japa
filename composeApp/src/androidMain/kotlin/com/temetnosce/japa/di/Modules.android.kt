package com.temetnosce.japa.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.temetnosce.japa.AndroidLocalizationProvider
import com.temetnosce.japa.LocalizationProvider
import com.temetnosce.japa.data.local.ChantedRoundsLocalDataSource
import com.temetnosce.japa.data.local.SettingsDataSourceImpl
import com.temetnosce.japa.data.local.ShlokasLocalDataSource
import com.temetnosce.japa.db.LocalDb
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource
import com.temetnosce.japa.domain.datasource.SettingsDataSource
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {

    single {
        AndroidSqliteDriver(
            schema = LocalDb.Schema,
            context = androidContext(),
            name = "chanted_rounds.db"
        )
    }

    singleOf(::AndroidLocalizationProvider) bind LocalizationProvider::class

    singleOf(::ChantedRoundsLocalDataSource) bind ChantedRoundsDataSource.Local::class

    singleOf(::SettingsDataSourceImpl) bind SettingsDataSource::class

    factoryOf(::ShlokasLocalDataSource) bind ShlokasDataSource.Local::class
}
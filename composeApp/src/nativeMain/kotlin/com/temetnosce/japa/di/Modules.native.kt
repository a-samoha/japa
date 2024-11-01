package com.temetnosce.japa.di

import com.temetnosce.japa.data.local.ShlokasLocalDataSource
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {

    factoryOf(::ShlokasLocalDataSource) bind ShlokasDataSource.Local::class
}
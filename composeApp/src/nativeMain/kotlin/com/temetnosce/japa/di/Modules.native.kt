package com.temetnosce.japa.di

import com.temetnosce.japa.data.local.ChantedRoundsLocalDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ChantedRoundsLocalDataSource)
}
package di

import data.local.ChantedRoundsLocalDataSource
import data.local.ShlokasLocalDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ChantedRoundsLocalDataSource)
    singleOf(::ShlokasLocalDataSource)
}
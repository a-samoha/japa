package di

import dependencies.ChantedRoundsRepository
import dependencies.ChantedRoundsRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.screen.home.HomeViewModel

expect val platformModule: Module

val sharedModule = module {
    singleOf(::ChantedRoundsRepositoryImpl).bind<ChantedRoundsRepository>()
    viewModelOf(::HomeViewModel)
}
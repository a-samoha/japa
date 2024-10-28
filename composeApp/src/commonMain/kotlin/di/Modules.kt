package di

import data.repository.ChantedRoundsRepositoryImpl
import data.repository.ShlokasRepositoryImpl
import domain.repository.ChantedRoundsRepository
import domain.repository.ShlokasRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.screen.home.HomeViewModel

expect val platformModule: Module

val sharedModule = module {

    singleOf(::ChantedRoundsRepositoryImpl).bind<ChantedRoundsRepository>()
    singleOf(::ShlokasRepositoryImpl).bind<ShlokasRepository>()

    viewModelOf(::HomeViewModel)
}
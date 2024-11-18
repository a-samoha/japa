package com.temetnosce.japa.di

import com.temetnosce.japa.data.repository.ChantedRoundsRepositoryImpl
import com.temetnosce.japa.data.repository.ShlokasRepositoryImpl
import com.temetnosce.japa.domain.repository.ChantedRoundsRepository
import com.temetnosce.japa.domain.repository.ShlokasRepository
import com.temetnosce.japa.presentation.screen.emend.EmendViewModel
import com.temetnosce.japa.presentation.screen.home.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    singleOf(::ChantedRoundsRepositoryImpl).bind<ChantedRoundsRepository>()
    singleOf(::ShlokasRepositoryImpl).bind<ShlokasRepository>()

    viewModelOf(::HomeViewModel)

    viewModel { (startTimestamp: Long) ->
        EmendViewModel(startTimestamp, get())
    }
}
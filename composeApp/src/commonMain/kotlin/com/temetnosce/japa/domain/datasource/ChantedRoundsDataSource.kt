package com.temetnosce.japa.domain.datasource

sealed interface ChantedRoundsDataSource {

    interface Local : ChantedRoundsDataSource

    interface Remote : ChantedRoundsDataSource
}
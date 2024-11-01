package com.temetnosce.japa.domain.usecase.home

import com.temetnosce.japa.domain.repository.ChantedRoundsRepository

class ObserveChantedRoundsUseCaseImpl(
    private val repository: ChantedRoundsRepository
) : ObserveChantedRoundsUseCase {
    override fun invoke(dayStartTimestamp: Long) = repository.observe(dayStartTimestamp)
}
package domain.usecase.home

import domain.repository.ChantedRoundsRepository

class ObserveChantedRoundsUseCaseImpl(
    private val repository: ChantedRoundsRepository
) : ObserveChantedRoundsUseCase {
    override fun invoke() = repository.observe()
}
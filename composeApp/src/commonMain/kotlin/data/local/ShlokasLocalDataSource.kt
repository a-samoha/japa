package data.local

import domain.entity.Shloka

expect class ShlokasLocalDataSource {
    fun loadShlokas(): List<Shloka>
}
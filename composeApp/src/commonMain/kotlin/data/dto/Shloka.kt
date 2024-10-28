package data.dto

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Shloka(
    val id: String = "",
    val shloka: String = "",
    val synonyms: String = "",
    val translation: String = "",
)

@Serializable
data class ShlokaList(
    val shlokas: List<Shloka>
)
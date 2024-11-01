package com.temetnosce.japa.data.dto

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class ShlokaDto(
    val id: String = "",
    val shloka: String = "",
    val synonyms: String = "",
    val translation: String = "",
)

@Serializable
data class ShlokaDtoList(
    val shlokas: List<ShlokaDto>
)
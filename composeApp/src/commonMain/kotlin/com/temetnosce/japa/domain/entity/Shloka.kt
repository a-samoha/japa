package com.temetnosce.japa.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Shloka(
    val id: String = "",
    val title: String = "",
    val shloka: String = "",
    val synonyms: String = "",
    val translation: String = "",
)
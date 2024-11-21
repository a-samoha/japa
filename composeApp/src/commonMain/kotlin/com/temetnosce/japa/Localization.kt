package com.temetnosce.japa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.temetnosce.japa.domain.entity.Language

val LocalLocalization = staticCompositionLocalOf { Language.English.isoFormat }

@Composable
fun LocalizedApp(
    language: String = Language.English.isoFormat,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        value = LocalLocalization provides language,
        content = content,
    )
}

interface LocalizationProvider {
    fun changeLang(language: Language)
}
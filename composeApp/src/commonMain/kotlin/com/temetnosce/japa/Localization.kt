package com.temetnosce.japa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.temetnosce.japa.domain.entity.Language

val LocalLocalization = staticCompositionLocalOf { Language.EN.name }

@Composable
fun LocalizedApp(
    language: String = Language.EN.name.lowercase(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLocalization provides language,
        content = content
    )
}

interface LocalizationProvider {
    fun changeLang(language: Language)
}

//@Composable
//fun LocalizedText(key: String) {
//    val settingsRepo: SettingsRepository = koinInject()
//    val localizationProvider: LocalizationProvider = koinInject()
//
//    val settings by settingsRepo.observe().collectAsStateWithLifecycle(settingsRepo.getSettings())
//    val context = LocalizationContext(settings.language)
//
//    val text = localizationProvider.getString(key, context)
//    Text(text = text)
//}

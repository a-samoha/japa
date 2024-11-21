package com.temetnosce.japa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.japa.domain.repository.SettingsRepository
import com.temetnosce.japa.presentation.theme.JapaAppTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
internal fun CommonCompose() {
    JapaAppTheme {

        val settingsRepo: SettingsRepository = koinInject()
        val settings by settingsRepo.observe()
            .collectAsStateWithLifecycle(settingsRepo.getSettings())

        LocalizedApp(
            language = settings.language.name.lowercase()
        ) {

            KoinContext {
                Navigation()
            }

        }
    }
}
package com.temetnosce.japa.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.japa.domain.repository.SettingsRepository
import com.temetnosce.japa.presentation.screen.settings.SettingsIntent.UpdateFlag.DarkTheme
import com.temetnosce.japa.presentation.screen.settings.SettingsIntent.UpdateFlag.SystemDarkTheme
import com.temetnosce.japa.presentation.screen.settings.SettingsIntent.UserLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SettingsViewModel(
    private val settingsRepo: SettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state
        .onStart { loadSettings() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = SettingsState(),
        )

    private fun loadSettings() {
        settingsRepo.observe()
            .onEach { settings ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        settings = settings,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun processIntent(intent: SettingsIntent) {

        settingsRepo.updateSettings(
            when (intent) {
                is UserLanguage -> state.value.settings.copy(language = intent.language)
                is SystemDarkTheme -> state.value.settings.copy(designUseSystemDarkTheme = intent.flag)
                is DarkTheme -> state.value.settings.copy(designDarkTheme = intent.flag)
            }
        )
            .onFailure { }
            .onSuccess { }
    }
}
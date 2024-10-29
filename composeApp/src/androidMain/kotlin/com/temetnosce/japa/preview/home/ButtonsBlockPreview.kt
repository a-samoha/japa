package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.temetnosce.japa.presentation.screen.home.components.ButtonsBlock
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState
import com.temetnosce.japa.presentation.theme.JapaAppTheme

@Composable
@PreviewLightDark
private fun ButtonsBlockPreview() {
    JapaAppTheme {
        ButtonsBlock(
            stopwatchState = StopWatchState.CHANT,
            onSettingsClick = {},
            onPlayStopClick = {},
            onPauseClick = {}
        )
    }
}
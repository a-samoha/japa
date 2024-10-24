package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import presentation.screen.home.components.ButtonsBlock
import presentation.screen.home.components.StopWatchState
import presentation.theme.JapaAppTheme

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
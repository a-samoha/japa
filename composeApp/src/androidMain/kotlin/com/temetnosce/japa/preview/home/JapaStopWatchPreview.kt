package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import presentation.screen.home.components.JapaStopWatch
import presentation.screen.home.components.StopWatchState
import presentation.theme.JapaAppTheme

@Composable
@PreviewLightDark
private fun JapaStopWatchPreview() {
    JapaAppTheme {
        JapaStopWatch(
            state = StopWatchState.CHANT,
            onStop = {},
        )
    }
}
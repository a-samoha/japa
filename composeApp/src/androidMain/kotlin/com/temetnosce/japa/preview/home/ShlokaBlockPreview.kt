package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import presentation.screen.home.components.ShlokaBlock
import presentation.theme.JapaAppTheme

@Composable
@PreviewLightDark
private fun ShlokaBlockPreview() {
    JapaAppTheme {
        ShlokaBlock()
    }
}
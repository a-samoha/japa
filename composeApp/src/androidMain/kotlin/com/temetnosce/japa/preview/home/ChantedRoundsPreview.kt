package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.presentation.screen.home.components.ChantedRounds
import com.temetnosce.japa.presentation.theme.JapaAppTheme

@Composable
@PreviewLightDark
private fun ChantedRoundsPreview() {
    JapaAppTheme {
        ChantedRounds(
            items = listOf(
                ChantedRound(1, "07:08", 5),
                ChantedRound(2, "05:08", 3)
            )
        )
    }
}
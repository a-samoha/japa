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
                ChantedRound(index = 1, duration = "07:08", points = 5),
                ChantedRound(index = 2, duration = "05:08", points = 3),
                ChantedRound(index = 3, duration = "05:08", points = 3),
                ChantedRound(index = 4, duration = "05:08", points = 3),
                ChantedRound(index = 5, duration = "05:08", points = 3),
                ChantedRound(index = 6, duration = "05:08", points = 3),
                ChantedRound(index = 7, duration = "05:08", points = 3),
                ChantedRound(index = 8, duration = "05:08", points = 3),
                ChantedRound(index = 9, duration = "05:08", points = 3),
                ChantedRound(index = 10, duration = "05:08", points = 3),
                ChantedRound(index = 11, duration = "05:08", points = 3),
                ChantedRound(index = 12, duration = "05:08", points = 3),
                ChantedRound(index = 13, duration = "05:08", points = 3),
                ChantedRound(index = 14, duration = "05:08", points = 3),
                ChantedRound(index = 15, duration = "05:08", points = 3),
                ChantedRound(index = 16, duration = "05:08", points = 3),
            )
        )
    }
}
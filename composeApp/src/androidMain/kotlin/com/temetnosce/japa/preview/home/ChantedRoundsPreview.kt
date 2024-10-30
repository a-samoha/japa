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
                ChantedRound(2, "05:08", 3),
                ChantedRound(3, "05:08", 3),
                ChantedRound(4, "05:08", 3),
                ChantedRound(5, "05:08", 3),
                ChantedRound(6, "05:08", 3),
                ChantedRound(7, "05:08", 3),
                ChantedRound(8, "05:08", 3),
                ChantedRound(9, "05:08", 3),
                ChantedRound(10, "05:08", 3),
                ChantedRound(11, "05:08", 3),
                ChantedRound(12, "05:08", 3),
                ChantedRound(13, "05:08", 3),
                ChantedRound(14, "05:08", 3),
                ChantedRound(15, "05:08", 3),
                ChantedRound(16, "05:08", 3),
            )
        )
    }
}
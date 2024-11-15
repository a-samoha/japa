package com.temetnosce.japa.preview.emend

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.presentation.screen.emend.EmendContent

@Preview(showSystemUi = true)
@Composable
private fun EmendScreenPreview() {
    EmendContent(
        state = ChantedRound(),
        onDurationChanged = {},
        onPointsChanged = {},
        onAccept = {},
        onCanсel = {},
    )
}
package com.temetnosce.japa.preview.emend

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.temetnosce.japa.presentation.screen.emend.EmendContent
import com.temetnosce.japa.presentation.screen.emend.EmendState

@Preview(showSystemUi = true)
@Composable
private fun EmendScreenPreview() {
    EmendContent(
        state = EmendState(),
        onRoundUpdate = {},
        onAccept = {},
    )
}
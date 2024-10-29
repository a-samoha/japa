package com.temetnosce.japa.preview.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.temetnosce.japa.presentation.screen.home.components.JapaPointsDialog
import com.temetnosce.japa.presentation.theme.JapaAppTheme

@Composable
@PreviewLightDark
private fun JapaPointsDialogPreview() {
    JapaAppTheme {
        JapaPointsDialog(
            showDialog = false,
            onDismissRequest = {}
        )
    }
}
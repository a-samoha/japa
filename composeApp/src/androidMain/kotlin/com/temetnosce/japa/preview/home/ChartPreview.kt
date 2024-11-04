package com.temetnosce.japa.preview.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.presentation.screen.home.components.Chart
import com.temetnosce.japa.presentation.theme.JapaAppTheme

@Composable
@Preview(showBackground = true)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
private fun ChartPreview() {
    JapaAppTheme {
        Chart(
            items = listOf(
                ChantedRound(index = 1, duration = "07:08", points = 5),
                ChantedRound(index = 2, duration = "05:08", points = 3)
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}
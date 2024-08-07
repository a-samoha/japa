package presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.screen.home.composable.ButtonsBlock
import presentation.screen.home.composable.ChantedRounds
import presentation.screen.home.composable.Chart
import presentation.screen.home.composable.ShlokaBlock
import presentation.screen.home.composable.TimerBox

@Composable
internal fun HomeScreen() {

    var showContent by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth().height(194.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            TimerBox(Modifier.weight(1f).fillMaxSize())
            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier
                    .width(1.dp)
            )
            ChantedRounds()
        }
        HorizontalDivider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )
        Chart(
            items = chantedRounds(),
            modifier = Modifier.fillMaxWidth().height(180.dp),
        )
        ButtonsBlock(Modifier.fillMaxWidth().height(140.dp))
        ShlokaBlock()
    }
}
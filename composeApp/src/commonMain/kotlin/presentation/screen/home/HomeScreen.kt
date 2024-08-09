package presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import presentation.screen.home.composable.StopWatch
import presentation.screen.home.composable.StopWatchState.CHANT
import presentation.screen.home.composable.StopWatchState.PAUSE
import presentation.screen.home.composable.StopWatchState.STOP
import presentation.screen.home.model.chantedRounds

@Composable
internal fun HomeScreen() {

    var showContent by remember { mutableStateOf(false) }

    var stopwatchState by remember { mutableStateOf(STOP) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth().height(194.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            StopWatch(
                modifier = Modifier.weight(1f).fillMaxSize(),
                state = stopwatchState,
                onStop = { startTime, elapsedTime, endTime -> println("startTime $startTime, elapsedTime $elapsedTime, endTime $endTime") }
            )
            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier.width(1.dp).padding(top = 16.dp)
            )
            ChantedRounds()
        }
        HorizontalDivider(
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth().width(1.dp).padding(horizontal = 16.dp)
        )
        Chart(
            items = chantedRounds(),
            modifier = Modifier.padding(start = 16.dp).fillMaxWidth().height(180.dp),
        )
        ButtonsBlock(
            Modifier.fillMaxWidth().height(140.dp),
            onSettingsClick = { println("test onSettingsClick") },
            onPlayStopClick = { stopwatchState = if (stopwatchState != CHANT) CHANT else STOP },
            onPauseClick = { stopwatchState = PAUSE },
        )
        ShlokaBlock(Modifier.weight(1f).fillMaxSize())
    }
}
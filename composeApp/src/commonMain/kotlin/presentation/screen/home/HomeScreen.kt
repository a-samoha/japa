package presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import lackner.BatteryManager
import presentation.screen.home.composable.ButtonsBlock
import presentation.screen.home.composable.ChantedRounds
import presentation.screen.home.composable.Chart
import presentation.screen.home.composable.JapaPointsDialog
import presentation.screen.home.composable.JapaStopWatch
import presentation.screen.home.composable.ShlokaBlock
import presentation.screen.home.composable.StopWatchState.CHANT
import presentation.screen.home.composable.StopWatchState.DEFAULT
import presentation.screen.home.composable.StopWatchState.PAUSE
import presentation.screen.home.composable.StopWatchState.STOP
import presentation.screen.home.model.ChantedRound

@Composable
internal fun HomeScreen(
    batteryManager: BatteryManager,
) {

    var chantedRoundsState by remember { mutableStateOf(emptyList<ChantedRound>()) }
    val showJapaPointsDialogState = remember { mutableStateOf(false) }
    val stopwatchState = remember { mutableStateOf(DEFAULT) }

    var lastChantedRound: ChantedRound? = null

    Column {
        // region JapaStopWatch
        Row(
            Modifier.fillMaxWidth().height(194.dp),
        ) {
            JapaStopWatch(
                modifier = Modifier.weight(1f).fillMaxSize(),
                state = stopwatchState.value,
                onStop = { cr ->
                    lastChantedRound = cr.copy(index = chantedRoundsState.size + 1)
                    showJapaPointsDialogState.value = true
                })

            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier.padding(top = 16.dp)
            )

            ChantedRounds(items = chantedRoundsState)
        }

        HorizontalDivider(
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Chart(
            items = chantedRoundsState,
            modifier = Modifier.padding(start = 16.dp).fillMaxWidth().height(180.dp),
        )

        /**
         * Buttons do not know about [JapaStopWatch]
         * Look for chanting results in StopWatch onStop() lambda.
         */
        ButtonsBlock(
            Modifier.fillMaxWidth().height(140.dp),
            stopwatchState,
            onSettingsClick = { println("test onSettingsClick") },
            onPlayStopClick = {
                stopwatchState.value = if (stopwatchState.value != CHANT) CHANT else STOP
            },
            onPauseClick = { stopwatchState.value = PAUSE },
        )

        ShlokaBlock(Modifier.weight(1f).fillMaxSize())

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "The current battery level is ${batteryManager.getBatteryLevel()}"
            )
        }
    }

    JapaPointsDialog(
        showDialog = showJapaPointsDialogState,
    ) { chosenPoints ->
        lastChantedRound = lastChantedRound?.copy(points = chosenPoints)
        lastChantedRound?.let { chantedRoundsState = chantedRoundsState + it }
        if (stopwatchState.value != CHANT) stopwatchState.value = CHANT
    }
}
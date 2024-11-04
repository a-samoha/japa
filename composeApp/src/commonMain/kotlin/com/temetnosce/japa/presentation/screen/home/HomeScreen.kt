package com.temetnosce.japa.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.presentation.screen.home.components.ButtonsBlock
import com.temetnosce.japa.presentation.screen.home.components.ChantedRounds
import com.temetnosce.japa.presentation.screen.home.components.Chart
import com.temetnosce.japa.presentation.screen.home.components.JapaPointsDialog
import com.temetnosce.japa.presentation.screen.home.components.JapaStopWatch
import com.temetnosce.japa.presentation.screen.home.components.ShlokaBlock
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState.CHANT
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState.PAUSE
import com.temetnosce.japa.presentation.screen.home.components.StopWatchState.STOP
import com.temetnosce.japa.presentation.screen.home.components.formatNoHours
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.shloka_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var lastChantedRound: ChantedRound? by remember { mutableStateOf(null) }

    HomeContent(
        state = state,
        onJapaStopwatchStop = { pair ->
            lastChantedRound = lastChantedRound?.copy(
                index = state.chantedRounds.size + 1,
                startTimestamp = pair.first,
                endTimestamp = pair.first + pair.second,
                duration = pair.second.formatNoHours(),
            )
            lastChantedRound?.let { viewModel.addChantedRound(it) }
            viewModel.setStopwatchState(CHANT)
        },
        onPauseClick = { viewModel.setStopwatchState(PAUSE) },
        onPlayStopClick = {
            if (state.stopWatchState != CHANT) {
                viewModel.setStopwatchState(CHANT)
            } else {
                viewModel.showJapaPointsDialog(true)
            }
        },
        onJapaPointsDialogDismiss = { chosenPoint ->
            if (chosenPoint == 0.toByte()) {
                viewModel.setStopwatchState(CHANT)
            } else {
                lastChantedRound = ChantedRound(points = chosenPoint)
                viewModel.setStopwatchState(STOP)
            }
            viewModel.showJapaPointsDialog(false)
        },
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    onJapaStopwatchStop: (Pair<Long, Long>) -> Unit,
    onPauseClick: () -> Unit,
    onPlayStopClick: () -> Unit,
    onJapaPointsDialogDismiss: (chosenPoint: Byte) -> Unit,
) {

    Column {
        Row(
            Modifier.fillMaxWidth().height(194.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row {
                    Text("total: ")
                    Text(state.totalTime)
                }
                JapaStopWatch(
                    state = state.stopWatchState,
                    onStop = { onJapaStopwatchStop(it) }
                )
                Row {
                    Text("best: ")
                    Text(state.bestRound)
                }
            }
            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier.padding(top = 16.dp)
            )
            ChantedRounds(items = state.chantedRounds)
        }

        HorizontalDivider(
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Chart(
            items = state.chantedRounds,
            modifier = Modifier
                .padding(start = if (state.chantedRounds.size <= 64) 16.dp else 12.dp)
                .fillMaxWidth()
                .height(180.dp),
        )

        /**
         * Buttons do not know about [JapaStopWatch]
         * Look for chanting results in StopWatch onStop() lambda.
         */
        ButtonsBlock(
            Modifier.fillMaxWidth().height(140.dp),
            state.stopWatchState,
            onSettingsClick = { println("test onSettingsClick") },
            onPauseClick = onPauseClick,
            onPlayStopClick = onPlayStopClick,
        )

        ShlokaBlock(
            shloka = state.shloka.let {
                it.copy(
                    title = stringResource(
                        Res.string.shloka_title,
                        it.id
                    )
                )
            },
            modifier = Modifier.weight(1f).fillMaxSize()
        )
    }

    JapaPointsDialog(
        showDialog = state.isPointsDialogVisible,
    ) { chosenPoint ->
        onJapaPointsDialogDismiss(chosenPoint)
    }
}
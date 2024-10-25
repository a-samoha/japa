package presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import domain.entity.ChantedRound
import org.koin.compose.viewmodel.koinViewModel
import presentation.screen.home.components.ButtonsBlock
import presentation.screen.home.components.ChantedRounds
import presentation.screen.home.components.Chart
import presentation.screen.home.components.JapaPointsDialog
import presentation.screen.home.components.JapaStopWatch
import presentation.screen.home.components.ShlokaBlock
import presentation.screen.home.components.StopWatchState.CHANT
import presentation.screen.home.components.StopWatchState.PAUSE
import presentation.screen.home.components.StopWatchState.STOP

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var lastChantedRound: ChantedRound? by remember { mutableStateOf(null) }

    HomeContent(
        state = state,
        onJapaStopwatchStop = { cr ->
            lastChantedRound = cr.copy(index = state.chantedRounds.size + 1)
            viewModel.showJapaPointsDialog(true)
        },
        onPauseClick = { viewModel.setStopwatchState(PAUSE) },
        onPlayStopClick = { viewModel.setStopwatchState(if (state.stopWatchState != CHANT) CHANT else STOP) },
        onJapaPointsDialogDismiss = { chosenPoint ->
            lastChantedRound = lastChantedRound?.copy(points = chosenPoint)
            lastChantedRound?.let { viewModel.addChantedRound(it) } /*state.chantedRounds = state.chantedRounds + it*/
            if (state.stopWatchState != CHANT) viewModel.setStopwatchState(CHANT)
            viewModel.showJapaPointsDialog(false)
        },
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    onJapaStopwatchStop: (chantedRound: ChantedRound) -> Unit,
    onPauseClick: () -> Unit,
    onPlayStopClick: () -> Unit,
    onJapaPointsDialogDismiss: (chosenPoint: Int) -> Unit,
) {

    Column {
        Row(
            Modifier.fillMaxWidth().height(194.dp),
        ) {
            JapaStopWatch(
                modifier = Modifier.weight(1f).fillMaxSize(),
                state = state.stopWatchState,
                onStop = { onJapaStopwatchStop(it) }
            )

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
            modifier = Modifier.padding(start = 16.dp).fillMaxWidth().height(180.dp),
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

        ShlokaBlock(Modifier.weight(1f).fillMaxSize())
    }

    JapaPointsDialog(
        showDialog = state.isPointsDialogVisible,
    ) { chosenPoint ->
        onJapaPointsDialogDismiss(chosenPoint)
    }
}
package presentation.screen.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import domain.entity.ChantedRound
import utils.currentTimestamp

enum class StopWatchState {
    DEFAULT, CHANT, PAUSE, STOP
}

@Composable
internal fun JapaStopWatch(
    modifier: Modifier = Modifier,
    state: StopWatchState,
    onStop: (ChantedRound) -> Unit
) {
    var startTime by remember { mutableStateOf<Long?>(null) }
    var elapsedTime by remember { mutableStateOf(0L) }
    var running by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        when (state) {
            StopWatchState.DEFAULT -> Unit
            StopWatchState.CHANT -> {
                if (!running) {
                    if (startTime == null) startTime = currentTimestamp()
                    running = true
                }
                while (running) {
                    delay(1000L)
                    elapsedTime += 1
                }
            }
            StopWatchState.PAUSE -> {
                running = false
            }
            StopWatchState.STOP -> {
                running = false
                onStop(
                    ChantedRound(
                        duration = elapsedTime.toFormatedString(),
                        startTimestamp = startTime ?: 0L,
                        endTimestamp = currentTimestamp()
                    )
                )
                startTime = null
                elapsedTime = 0L
            }
        }
    }

    Box(
        modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = elapsedTime.toFormatedString(),
            fontSize = 40.sp
        )
    }
}

internal fun Long.toFormatedString(): String {
    return when {
        this / 60 < 10 && this % 60 < 10 -> "0${this / 60}:0${this % 60}"
        this / 60 < 10 && this % 60 > 9 -> "0${this / 60}:${this % 60}"
        this / 60 > 9 && this % 60 < 10 -> "${this / 60}:0${this % 60}"
        else -> "${this / 60}:${this % 60}"
    }
}
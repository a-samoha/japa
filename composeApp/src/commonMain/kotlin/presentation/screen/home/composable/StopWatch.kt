package presentation.screen.home.composable

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
import kotlinx.datetime.Clock

enum class StopWatchState {
    CHANT, PAUSE, STOP
}

@Composable
internal fun StopWatch(
    modifier: Modifier = Modifier,
    state: StopWatchState,
    onStop: (Long, Long, Long) -> Unit
) {
    var startTime by remember { mutableStateOf<Long?>(null) }
    var elapsedTime by remember { mutableStateOf(0L) }
    var running by remember { mutableStateOf(false) }

    fun currentTimeMillis() = Clock.System.now().epochSeconds

    LaunchedEffect(state) {
        when (state) {
            StopWatchState.CHANT -> {
                if (!running) {
                    startTime = currentTimeMillis()
                    running = true
                }
                while (running) {
                    delay(1000L) // Задержка в 1 секунду
                    elapsedTime += currentTimeMillis() - (startTime ?: currentTimeMillis())
                    startTime = currentTimeMillis()
                }
            }
            StopWatchState.PAUSE -> {
                if (running) {
                    elapsedTime += currentTimeMillis() - (startTime ?: currentTimeMillis())
                    running = false
                }
            }
            StopWatchState.STOP -> {
                if (running) {
                    elapsedTime += currentTimeMillis() - (startTime ?: currentTimeMillis())
                    running = false
                }
                val endTime = currentTimeMillis()
                val duration = elapsedTime
                onStop(startTime ?: endTime, duration, endTime)
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
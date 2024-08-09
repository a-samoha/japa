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
                if (startTime == null) startTime = currentTimeMillis()//System.currentTimeMillis()
                running = true
                while (running) {
                    delay(1000L) // Delay for 1 second
                    elapsedTime = currentTimeMillis() - (startTime ?: currentTimeMillis())
                }
            }
            StopWatchState.PAUSE -> {
                running = false
            }
            StopWatchState.STOP -> {
                running = false
                if (startTime != null) {
                    val endTime = currentTimeMillis()
                    val duration = endTime - (startTime ?: endTime)
                    onStop(startTime!!, duration, endTime)
                }
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
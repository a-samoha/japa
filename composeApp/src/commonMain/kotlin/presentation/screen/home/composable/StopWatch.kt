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
//        val totalSeconds = elapsedTime
//        val minutes = totalSeconds / 60
//        val seconds = totalSeconds % 60
//        val timeFormatted = "$minutes:$seconds" //String.format("%02d:%02d", minutes, seconds)

        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = "0${elapsedTime / 60}:0${elapsedTime % 60}",
            fontSize = 40.sp
        )
    }
}
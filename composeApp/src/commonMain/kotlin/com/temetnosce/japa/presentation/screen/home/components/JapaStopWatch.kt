package com.temetnosce.japa.presentation.screen.home.components

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
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.utils.currentTimestamp
import kotlinx.coroutines.delay

enum class StopWatchState {
    DEFAULT, CHANT, PAUSE, STOP
}

@Composable
internal fun JapaStopWatch(
    modifier: Modifier = Modifier,
    state: StopWatchState,
    onStop: (ChantedRound) -> Unit,
) {
    var startTime by remember { mutableStateOf(0L) } // 8 byte of RAM (random access memory)
    var elapsedTime by remember { mutableStateOf(0L) } // 4 byte

    /**
     * todo
     * QUESTIONS:
     * 1 - Why recomposition running 1500 times when LaunchedEffect(Unit){} moved to the CHANT brunch?
     * 2 - Why JapaStopWatch recomposition also take place on `elapsedTimeSec` change? (I think only Text has to recomposition)
     */
    LaunchedEffect(state) {
        when (state) {
            StopWatchState.DEFAULT,
            StopWatchState.PAUSE -> Unit
            StopWatchState.CHANT -> {
                if (startTime == 0L) startTime = currentTimestamp()
                while (true) {
                    delay(1000L)
                    elapsedTime = (currentTimestamp() - startTime)
                }
            }
            StopWatchState.STOP -> {
                onStop(
                    ChantedRound(
                        duration = elapsedTime.formatNoHours(),
                        startTimestamp = startTime,
                        endTimestamp = currentTimestamp()
                    )
                )
                startTime = 0L
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
            text = elapsedTime.formatNoHours(),
            fontSize = 40.sp
        )
    }
}

fun Long.formatNoHours(): String {
    val timeSec = this / 1000
    return when {
        timeSec / 60 < 10 && timeSec % 60 < 10 -> "0${timeSec / 60}:0${timeSec % 60}"
        timeSec / 60 < 10 && timeSec % 60 > 9 -> "0${timeSec / 60}:${timeSec % 60}"
        timeSec / 60 > 9 && timeSec % 60 < 10 -> "${timeSec / 60}:0${timeSec % 60}"
        else -> "${timeSec / 60}:${timeSec % 60}"
    }
}

fun Long.formatWithHours(): String {
    val timeSec = this / 1000
    val hours = timeSec / 3600
    val minutes = (timeSec % 3600) / 60
    val seconds = timeSec % 60

    // return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    return "${
        hours.toString().padStart(2, '0')
    }:${
        minutes.toString().padStart(2, '0')
    }:${
        seconds.toString().padStart(2, '0')
    }"
}
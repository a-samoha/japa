package com.temetnosce.japa.presentation.screen.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.temetnosce.japa.utils.currentTimestamp
import com.temetnosce.japa.utils.formatNoHours
import kotlinx.coroutines.delay

enum class StopWatchState {
    DEFAULT, CHANT, PAUSE, STOP
}

@Composable
internal fun JapaStopWatch(
    modifier: Modifier = Modifier,
    state: StopWatchState,
    onStop: (Pair<Long, Long>) -> Unit,
) {
    var startTimestamp by rememberSaveable { mutableStateOf(0L) }
    var pauseTimestamp by remember { mutableStateOf(0L) }
    var elapsedTime by remember { mutableStateOf(0L) }
    var pauseTime by remember { mutableStateOf(0L) }

    fun resetAll() {
        startTimestamp = 0L
        pauseTimestamp = 0L
        elapsedTime = 0L
        pauseTime = 0L
    }

    /**
     * todo
     * QUESTIONS:
     * 1 - Why recomposition running 1500 times when LaunchedEffect(Unit){} moved to the CHANT brunch?
     * 2 - Why JapaStopWatch recomposition also take place on `elapsedTimeSec` change? (I think only Text has to recomposition)
     */
    LaunchedEffect(state) {
        when (state) {
            StopWatchState.DEFAULT -> resetAll()
            StopWatchState.PAUSE -> pauseTimestamp = currentTimestamp()
            StopWatchState.CHANT -> {
                if (pauseTimestamp != 0L) {
                    pauseTime += (currentTimestamp() - pauseTimestamp)
                    pauseTimestamp = 0L
                }
                if (startTimestamp == 0L) startTimestamp = currentTimestamp()
                while (true) {
                    delay(1000L)
                    elapsedTime = (currentTimestamp() - startTimestamp) - pauseTime
                }
            }
            StopWatchState.STOP -> {
                pauseTimestamp = 0L
                onStop(startTimestamp to elapsedTime)
                resetAll()
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

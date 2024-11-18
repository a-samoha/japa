package com.temetnosce.japa.presentation.screen.emend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.japa.LocalNavController
import com.temetnosce.japa.domain.entity.ChantedRound
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

// Emend is a synonym of 'revise'

@Composable
internal fun EmendScreen(
    startTimestamp: Long = 0L,
    viewModel: EmendViewModel = koinViewModel(parameters = { parametersOf(startTimestamp) })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmendContent(
        state,
        onRoundUpdate = { viewModel.onRoundUpdate(it) },
        onAccept = { viewModel.onAccept() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmendContent(
    state: EmendState,
    onRoundUpdate: (updatedRound: ChantedRound) -> Unit,
    onAccept: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }
    val pointsOptions = (1..10).toList()
    val navController = LocalNavController.current

    when (state.isUpdatedSuccessfully) {
        true -> navController.popBackStack()
        false -> Unit // todo show error msg
        null -> Unit
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {}
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = "Duration",
                    fontSize = 20.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Min")
                        TextField(
                            value = state.chantedRound.duration.substringBefore(":"),
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    onRoundUpdate(
                                        updateDuration(state.chantedRound, min = it)
                                    )
                                }
                            },
                            modifier = Modifier.width(60.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Sec")
                        TextField(
                            value = state.chantedRound.duration.substringAfter(":"),
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    if ((it.toByteOrNull() ?: 0) < 60) {
                                        onRoundUpdate(
                                            updateDuration(state.chantedRound, sec = it)
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.width(60.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                Text(
                    "Points",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    Text(
                        modifier = Modifier
                            .width(80.dp)
                            .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true),
                        text = state.chantedRound.points.toString(),
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        pointsOptions.forEach { point ->
                            DropdownMenuItem(
                                text = { Text(point.toString()) },
                                onClick = {
                                    onRoundUpdate(state.chantedRound.copy(points = point.toByte()))
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }


            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { navController.popBackStack() },
            ) {
                Text("Cancel")
            }

            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onAccept,
            ) {
                Text("Ok")
            }
        }
    }
}

private fun updateDuration(
    round: ChantedRound,
    min: String? = null,
    sec: String? = null
): ChantedRound {
    val secondsInDuration = (round.endTimestamp - round.startTimestamp) / 1000 % 60
    val minutesInDuration = (round.endTimestamp - round.startTimestamp) / 1000 / 60

    if (min?.isEmpty() == true) {
        val newEndTimestamp = round.startTimestamp + (secondsInDuration * 1000)
        return round.copy(
            endTimestamp = newEndTimestamp,
            duration = (min + ":" + round.duration.substringAfter(":"))
        )
    } else {
        min?.toLongOrNull()?.times(60_000)?.let {
            val newEndTimestamp = round.startTimestamp + it + (secondsInDuration * 1000)
            return round.copy(
                endTimestamp = newEndTimestamp,
                duration = (min + ":" + round.duration.substringAfter(":"))
            )
        }
    }

    if (sec?.isEmpty() == true) {
        val newEndTimestamp = round.startTimestamp + minutesInDuration.times(60_000)
        return round.copy(
            endTimestamp = newEndTimestamp,
            duration = round.duration.substringBefore(":") + ":" + sec
        )
    } else {
        sec?.toLongOrNull()?.times(1_000)?.let {
            val newEndTimestamp = round.startTimestamp + minutesInDuration.times(60_000) + it
            return round.copy(
                endTimestamp = newEndTimestamp,
                duration = round.duration.substringBefore(":") + ":" + sec
            )
        }
    }

    return round
}
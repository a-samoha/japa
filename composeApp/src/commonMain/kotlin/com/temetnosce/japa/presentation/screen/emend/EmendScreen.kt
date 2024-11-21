package com.temetnosce.japa.presentation.screen.emend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.japa.LocalNavController
import com.temetnosce.japa.domain.entity.ChantedRound
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.cancel
import japa.composeapp.generated.resources.delete
import japa.composeapp.generated.resources.dscArrowBack
import japa.composeapp.generated.resources.duration
import japa.composeapp.generated.resources.edit_round
import japa.composeapp.generated.resources.min
import japa.composeapp.generated.resources.ok
import japa.composeapp.generated.resources.points
import japa.composeapp.generated.resources.sec
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


/**
 * Emend is a synonym of 'revise' or 'edit'
 */
@Composable
internal fun EmendScreen(
    startTimestamp: Long = 0L,
    viewModel: EmendViewModel = koinViewModel(parameters = { parametersOf(startTimestamp) })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    EmendContent(
        state,
        onRoundUpdate = { viewModel.onRoundUpdate(it) },
        onDelete = { viewModel.onDelete() },
        onCancel = { navController.popBackStack() },
        onAccept = { viewModel.onAccept() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmendContent(
    state: EmendState,
    onRoundUpdate: (updatedRound: ChantedRound) -> Unit = {},
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {},
    onAccept: () -> Unit = {},
) {

    var expanded by remember { mutableStateOf(false) }
    val pointsOptions = (1..10).toList()

    when (state.isUpdatedSuccessfully) {
        true -> onCancel()
        false -> Unit // todo show error msg
        null -> Unit
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onCancel) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(Res.string.dscArrowBack),
                                tint = Color.DarkGray,
                                modifier = Modifier.size(48.dp).padding(8.dp),
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(Res.string.edit_round),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    },
                    windowInsets = WindowInsets(0, 0, 0, 0),
                )
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
// System navigation bars considering
//                .systemBarsPadding()
//                .imePadding()
//                .navigationBarsPadding(),

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
                    text = stringResource(Res.string.duration),
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
                        Text(stringResource(Res.string.min))
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
                        Text(stringResource(Res.string.sec))
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
                    text = stringResource(Res.string.points),
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
                            .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
                            .fillMaxWidth(),
                        text = state.chantedRound.points.toString(),
                        textAlign = TextAlign.Center
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        pointsOptions.forEach { point ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = point.toString(),
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                },
                                onClick = {
                                    onRoundUpdate(state.chantedRound.copy(points = point.toByte()))
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onDelete,
                ) {
                    Text(
                        text = stringResource(Res.string.delete)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onCancel,
                ) {
                    Text(stringResource(Res.string.cancel))
                }
                Spacer(modifier = Modifier.width(24.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onAccept,
                ) {
                    Text(stringResource(Res.string.ok))
                }
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
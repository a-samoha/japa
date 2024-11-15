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
import com.temetnosce.japa.domain.entity.ChantedRound
import org.koin.compose.viewmodel.koinViewModel

// Emend is a synonym of 'revise'

@Composable
internal fun EmendScreen(
    startTimestamp: Long = 0L,
    viewModel: EmendViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmendContent(
        state,
        onDurationChanged = {},
        onPointsChanged = {},
        onAccept = {},
        onCanсel = {},
    )
}

@Composable
fun EmendContent(
    state: ChantedRound,
    onDurationChanged: () -> Unit,
    onPointsChanged: () -> Unit,
    onAccept: () -> Unit,
    onCanсel: () -> Unit,
) {

    var min by remember { mutableStateOf("") }
    var sec by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }

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
                Text("Duration", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Мин")
                        TextField(
                            value = min,
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    min = it
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
                            value = sec,
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    sec = it
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

                TextField(
                    value = points,
                    onValueChange = {
                        if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                            val number = it.toIntOrNull() ?: 0
                            if (number in 1..10) points = it
                        }
                    },
                    modifier = Modifier.width(80.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }


            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = onCanсel,
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

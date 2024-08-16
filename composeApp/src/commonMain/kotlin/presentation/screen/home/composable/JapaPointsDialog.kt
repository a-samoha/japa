package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun JapaPointsDialog(
    showDialog: MutableState<Boolean>,
    onDismissRequest: (chosenPoints: Int) -> Unit,
) {

    val pointsList = listOf(
        "10" to 10,
        "9" to 9,
        "8" to 8,
        "7" to 7,
        "6" to 6,
        "5" to 5,
        "4" to 4,
        "3" to 3,
        "2" to 2,
        "1" to 1
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Chanted Rounds Points") },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    items(pointsList.size) { index ->
                        Text(
                            text = "${pointsList[index].second} points",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                                .clickable {
                                    onDismissRequest(pointsList[index].second)
                                    showDialog.value = false
                                }
                        )
                    }
                }
            },
            confirmButton = {},
            containerColor = Color.White,
            shape = MaterialTheme.shapes.small,
        )
    }
}
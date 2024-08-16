package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
        "Розум привабився смаком святих імен. Переважно повна увага" to 10,
        "Повторювання з емоцією смирення, поява смаку, ентузіазм" to 9,
        "Наполегливі зусилля. Молитва розкаювання, пошук смирення" to 8,
        "Розуміння розсіяності, зусилля в уважності попри різні думки" to 7,
        "Розсіяність. Інколи є розуміння неуважності. Нетривале зосередження. Планування справ. Прагнення мат. насолод та здобутків" to 6,
        "Розсіяність. Інколи є розуміння неуважності. Нетривале зосередження. Планування справ. Прагнення мат. насолод та здобутків" to 5,
        "Повна байдужість. Думки про мат. плоди. Розглядання речей навколо. Дуже нудне повторювання" to 4,
        "Повна байдужість. Думки про мат. плоди. Розглядання речей навколо. Дуже нудне повторювання" to 3,
        "Сильна сонливість, тупість, апатія, відраза до повторювання" to 2,
        "Сильна сонливість, тупість, апатія, відраза до повторювання" to 1
    )

    if (showDialog.value) {
        AlertDialog(
            modifier = Modifier.padding(vertical = 32.dp),
            onDismissRequest = {},
            title = { Text("Chanted Rounds Points") },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    items(pointsList.size) { index ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDismissRequest(pointsList[index].second)
                                showDialog.value = false
                            }) {
                            Text(
                                text = pointsList[index].first,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 8.dp)
                            )
                            Text(
                                text = "${pointsList[index].second}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {},
            containerColor = Color.White,
            shape = MaterialTheme.shapes.small,
        )
    }
}
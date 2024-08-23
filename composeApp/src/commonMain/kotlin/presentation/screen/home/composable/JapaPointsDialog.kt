package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaPointsDialog(
    showDialog: MutableState<Boolean>,
    onDismissRequest: (chosenPoints: Int) -> Unit,
) {
    if (showDialog.value) {
        BasicAlertDialog(
            modifier = Modifier.padding(vertical = 32.dp),
            onDismissRequest = {},
            content = {
                Card(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .horizontalScroll(rememberScrollState()),
                    shape = RoundedCornerShape(1.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.width(576.dp).height(820.dp),
                        constraintSet = constraints(),
                    ) {
                        Column(
                            modifier = Modifier.width(184.dp).rotate(-90f).layoutId("cheshta"),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontSize = 26.sp,
                                textAlign = TextAlign.Center,
                                text = "НАМА\nАБХАС"
                            )
                            HorizontalDivider(color = Color.Gray)
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                text = "ЧЕШТА"
                            )
                            Text(
                                textAlign = TextAlign.Center,
                                text = "зусилля, повага,\nстарання"
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier.width(192.dp).layoutId("dividerHor"),
                            thickness = 2.dp,
                            color = Color.Gray,
                        )
                        Column(
                            modifier = Modifier.width(184.dp).rotate(-90f).layoutId("pramada"),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontSize = 26.sp,
                                textAlign = TextAlign.Center,
                                text = "НАМА\nАПАРАДГА"
                            )
                            HorizontalDivider(color = Color.Gray)
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center, text = "ПРАМАДА"
                            )
                            Text(
                                textAlign = TextAlign.Center,
                                text = "байдужість, зневага,\nтупість, сон"
                            )
                        }
                        PointsColumn(
                            Modifier.layoutId("pointsColumn"),
                            showDialog,
                            onDismissRequest
                        )
                    }
                }
            },
        )
    }
}

private fun constraints(): ConstraintSet = ConstraintSet {
    val cheshta = createRefFor("cheshta")
    val dividerHor = createRefFor("dividerHor")
    val pramada = createRefFor("pramada")
    val pointsColumn = createRefFor("pointsColumn")
    val h40 = createGuidelineFromTop(fraction = 0.399f)

    constrain(cheshta) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(h40)
    }

    constrain(dividerHor) {
        centerAround(h40)
        start.linkTo(parent.start)
    }

    constrain(pramada) {
        top.linkTo(h40)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(pointsColumn) {
        end.linkTo(parent.end)
    }
}

@Composable
fun PointsColumn(
    modifier: Modifier,
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

    Column(
        modifier = modifier.width(380.dp),
    ) {
        for (item in pointsList) {
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .clickable {
                        onDismissRequest(item.second)
                        showDialog.value = false
                    }
                    .padding(vertical = 4.dp)
                    .background(
                        color = Color(
                            0.05882353f,
                            0.99215686f,
                            0.7176471f
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.first,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .width(354.dp)
                        .padding(end = 16.dp)
                )
                VerticalDivider(
                    thickness = 2.dp,
                    color = Color.Gray
                )
                Text(
                    text = "${item.second}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.size(24.dp)
                )
                VerticalDivider(
                    thickness = 2.dp,
                    color = Color.Gray
                )
            }
            HorizontalDivider(
                thickness = 2.dp,
                color = Color.Gray
            )
        }
    }
}
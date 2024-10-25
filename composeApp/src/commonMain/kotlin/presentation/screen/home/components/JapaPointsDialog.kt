package presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
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
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.absentmindedness
import japa.composeapp.generated.resources.complete_indifference
import japa.composeapp.generated.resources.emotion_of_humility
import japa.composeapp.generated.resources.persistent_efforts
import japa.composeapp.generated.resources.strong_drowsiness
import japa.composeapp.generated.resources.the_mind_was_attracted
import japa.composeapp.generated.resources.understanding_distraction
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaPointsDialog(
    showDialog: Boolean,
    onDismissRequest: (chosenPoint: Int) -> Unit,
) {
    val scrollingState = rememberScrollState()
    LaunchedEffect("scrollHorizontal") {
        scrollingState.animateScrollTo(800) // scroll 800 px
    }
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = {},
            content = {
                Card(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .horizontalScroll(scrollingState),
                    shape = RoundedCornerShape(1.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.width(520.dp).height(732.dp),
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
//                            showDialog,
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
    val h40 = createGuidelineFromTop(fraction = 0.402f)

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
//    showDialog: MutableState<Boolean>,
    onDismissRequest: (chosenPoints: Int) -> Unit,
) {
    val pointsList = listOf(
        Triple(
            stringResource(Res.string.the_mind_was_attracted),
            10,
            Color(0.05882353f, 0.99215686f, 0.7176471f)
        ),
        Triple(
            stringResource(Res.string.emotion_of_humility),
            9,
            Color(0.05882353f, 0.99215686f, 0.7176471f)
        ),
        Triple(
            stringResource(Res.string.persistent_efforts),
            8,
            Color(0.05882353f, 0.99215686f, 0.7176471f)
        ),
        Triple(
            stringResource(Res.string.understanding_distraction),
            7,
            Color(0.05882353f, 0.99215686f, 0.7176471f) // 15, 253, 183 RGB
        ),
        Triple(
            stringResource(Res.string.absentmindedness),
            65,
            Color(0.99215686f, 0.95686275f, 0f) // 253, 244, 0 RGB
        ),
        Triple(
            stringResource(Res.string.complete_indifference),
            43,
            Color(0.99215686f, 0.6549019f, 0.07058824f) // 253, 167, 18 RGB
        ),
        Triple(
            stringResource(Res.string.strong_drowsiness),
            21,
            Color(0.99215686f, 0.24705882f, 0.34117648f) // 253, 63, 87 RGB
        ),
    )

    Column(modifier = modifier.width(324.dp)) {
        for (point in pointsList) {
            PointRow(point, onDismissRequest)
        }
    }
}

@Composable
fun PointRow(
    triple: Triple<String, Int, Color>,
//    showDialog: MutableState<Boolean>,
    onDismissRequest: (chosenPoints: Int) -> Unit,
) {
    if (triple.second <= 10) {
        val point = triple.second
        Row(
            modifier = Modifier.height(pointRowHeight.dp)
                .clickable {
                    onDismissRequest(point)
//                    showDialog.value = false
                }.background(color = triple.third),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.width(pointDescrTextWidth.dp).padding(horizontal = padding.dp),
                text = triple.first,
                style = MaterialTheme.typography.bodyLarge,
            )
            VerticalDivider(
                thickness = 2.dp,
                color = Color.Gray,
            )
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "$point",
                    fontSize = pointDescrTextSize.sp,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            VerticalDivider(
                thickness = 2.dp,
                color = Color.Gray,
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Gray,
        )
    } else {
        Box(contentAlignment = Alignment.CenterStart) {
            Column {
                val points = "${triple.second}".map { it.digitToInt() }
                for (point in points) {
                    Row(
                        modifier = Modifier.height(pointRowHeight.dp)
                            .clickable {
                                onDismissRequest(point)
//                                showDialog.value = false
                            }.background(color = triple.third),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(
                            modifier = Modifier.width(pointDescrTextWidth.dp)
                                .padding(horizontal = padding.dp)
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = Color.Gray,
                        )
                        Box(
                            modifier = Modifier.size(pointValueBoxSize.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "$point",
                                fontSize = pointDescrTextSize.sp,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                        VerticalDivider(
                            thickness = 2.dp,
                            color = Color.Gray,
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.width(pointDescrTextWidth.dp).padding(horizontal = padding.dp),
                text = triple.first,
                style = MaterialTheme.typography.bodyLarge,
            )
            HorizontalDivider(
                modifier = Modifier.padding(start = pointDescrTextWidth.dp),
                thickness = 2.dp,
                color = Color.Gray,
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Gray,
        )
    }
}

private const val pointRowHeight = 72
private const val pointValueBoxSize = 48
private const val pointDescrTextWidth = 272
private const val pointDescrTextSize = 28
private const val padding = 8

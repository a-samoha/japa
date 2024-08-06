package presentation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.CompositionContextLocal
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    var showContent by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.End,
        ) {
            TimerBox()
            ChantedRounds()
        }
    }
}

@Composable
fun TimerBox() {
    Box(
        Modifier.height(190.dp).wrapContentWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "08:03", modifier = Modifier.padding(horizontal = 56.dp), fontSize = 40.sp)
    }
}

@Composable
fun ChantedRounds() =
    LazyColumn(
        Modifier.height(190.dp).padding(top = 8.dp, end = 16.dp, bottom = 8.dp)
    ) {
        items(
            items = chantedRounds(),
            key = ChantedRound::index
        ) {
            Card(
                modifier = Modifier.padding(vertical = 8.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, Color.Gray),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "${it.index}.", fontSize = 20.sp)
                    Text(text = it.time, Modifier.padding(horizontal = 16.dp), fontSize = 20.sp)
                    CircularText(text = "${it.points}")
                }
            }
        }
    }

@Composable
fun CircularText(
    text: String,
    backgroundColor: Color = Color.Unspecified,
    textColor: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape // Change this to Rectangle for true Rectangle
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(32.dp)
            .background(color = backgroundColor, shape = shape)
            .border(1.dp, Color.Black, shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
        )
    }
}


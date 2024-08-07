package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.screen.home.ChantedRound
import presentation.screen.home.chantedRounds


@Composable
internal fun ChantedRounds(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val items = chantedRounds()

    // Scroll to the last item after first rendering
    LaunchedEffect(items) {
        listState.animateScrollToItem(items.size - 1)
    }

    val contentPadding = PaddingValues(start = 16.dp, top = 6.dp, end = 16.dp, bottom = 6.dp)

    LazyColumn(
        state = listState,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(
            items = chantedRounds(),
            key = ChantedRound::index
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(start = 14.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
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
    shape: Shape = CircleShape,
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

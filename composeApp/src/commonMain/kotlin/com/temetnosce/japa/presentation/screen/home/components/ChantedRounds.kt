package com.temetnosce.japa.presentation.screen.home.components

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
import com.temetnosce.japa.domain.entity.ChantedRound
import com.temetnosce.japa.presentation.theme.JapaAppTheme
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.chanted_rounds_hint
import japa.composeapp.generated.resources.shloka_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ChantedRounds(
    modifier: Modifier = Modifier,
    items: List<ChantedRound>,
) {
    // scroll to the last item after rendering
     val scrollingState = rememberLazyListState()
     LaunchedEffect(items) {
         scrollingState.animateScrollToItem(0)
     }

    val contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)

    LazyColumn(
        state = scrollingState,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        if (items.isEmpty()) {
            // show hint text
            item {
                Box(
                    modifier = Modifier
                        .size(174.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.chanted_rounds_hint),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        } else {
            items(
                items = items.reversed(),
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
                    Text(
                        text = if (it.index < 10) " ${it.index}." else "${it.index}.",
                        fontSize = 20.sp
                    )
                    Text(text = it.duration, Modifier.padding(horizontal = 16.dp), fontSize = 20.sp)
                    CircularText(text = "${it.points}")
                }
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

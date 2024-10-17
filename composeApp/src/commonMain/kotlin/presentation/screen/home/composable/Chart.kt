package presentation.screen.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import domain.entity.ChantedRound

@Composable
fun Chart(items: List<ChantedRound>, modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        val padding = 20.dp.toPx()
        val width = size.width - 2 * padding
        val height = size.height - 2 * padding
        val minX = 0f
        val maxX = 16f
        val minY = 0f
        val maxY = 10f

        fun scaleX(x: Int): Float {
            return (padding + (x - minX) / (maxX - minX) * width) - 28
        }

        fun scaleY(y: Int): Float {
            return padding + (maxY - y) / (maxY - minY) * height
        }

        // Draw axes
        drawLine(
            color = Color.Black,
            start = Offset(x = padding, y = padding - 16),
            end = Offset(x = padding, y = size.height - padding),
            strokeWidth = 2f
        )
        drawLine(
            color = Color.Black,
            start = Offset(x = padding, y = size.height - padding),
            end = Offset(x = size.width - padding, y = size.height - padding),
            strokeWidth = 2f
        )

        // Draw X and Y axis labels
        fun drawLabels(index: Int, x: Float, y: Float) = drawText(
            textMeasurer = textMeasurer,
            text = buildAnnotatedString { withStyle(ParagraphStyle()) { append("$index") } },
            topLeft = Offset(x, y)
        )

        drawLabels(1, scaleX(1) - 2, size.height - padding)
        drawLabels(8, scaleX(8) - 16, size.height - padding)
        drawLabels(16, scaleX(16) - 16, size.height - padding)
        drawLabels(1, 16f, scaleY(1) - 24)
        drawLabels(5, 13f, scaleY(5) - 24)
        drawLabels(10, 4f, scaleY(10) - 24)

        // Draw data points and lines
        items.forEachIndexed { index, item ->
            val x = scaleX(item.index)
            val y = scaleY(item.points)
            drawCircle(
                color = Color.Blue,
                radius = 5f,
                center = Offset(x, y)
            )
            if (index > 0) {
                val prevItem = items[index - 1]
                val prevX = scaleX(prevItem.index)
                val prevY = scaleY(prevItem.points)
                drawLine(
                    color = Color.Blue,
                    start = Offset(prevX, prevY),
                    end = Offset(x, y),
                    strokeWidth = 2f
                )
            }
        }
    }
}

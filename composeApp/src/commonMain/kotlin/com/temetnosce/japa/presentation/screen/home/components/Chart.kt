package com.temetnosce.japa.presentation.screen.home.components

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
import com.temetnosce.japa.domain.entity.ChantedRound

@Composable
fun Chart(
    items: List<ChantedRound>,
    modifier: Modifier = Modifier,
) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        val padding = 20.dp.toPx()
        val width = size.width - 2 * padding
        val height = size.height - 2 * padding
        val minX = 0f
        val maxX = when {
            items.size <= 16 -> 16f
            items.size <= 20 -> 20f
            items.size <= 32 -> 32f
            items.size <= 64 -> 64f
            else -> 128f
        }
        val minY = 0f
        val maxY = 10f

        fun scaleX(x: Int): Float {
            return (
                    when {
                        items.size <= 16 -> 10.dp.toPx()
                        items.size <= 20 -> 12.dp.toPx()
                        items.size <= 32 -> 14.dp.toPx()
                        items.size <= 64 -> 16.dp.toPx()
                        else -> 18.dp.toPx()
                    } + (x - minX) * width / (maxX - minX))
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

        when {
            items.size <= 16 -> {
                drawLabels(1, scaleX(1) - 3.dp.toPx(), size.height - padding)
                drawLabels(
                    (maxX / 2).toInt(),
                    scaleX((maxX / 2).toInt()) - 4.dp.toPx(),
                    size.height - padding
                )
                drawLabels(maxX.toInt(), scaleX(maxX.toInt()) - 6.dp.toPx(), size.height - padding)
            }
            items.size <= 20 -> {
                drawLabels(1, scaleX(1) - 3.dp.toPx(), size.height - padding)
                drawLabels(
                    (maxX / 2).toInt(),
                    scaleX((maxX / 2).toInt()) - 6.dp.toPx(),
                    size.height - padding
                )
                drawLabels(maxX.toInt(), scaleX(maxX.toInt()) - 7.dp.toPx(), size.height - padding)
            }
            items.size <= 32 -> {
                drawLabels(1, scaleX(1) - 3.dp.toPx(), size.height - padding)
                drawLabels(
                    (maxX / 2).toInt(),
                    scaleX((maxX / 2).toInt()) - 7.dp.toPx(),
                    size.height - padding
                )
                drawLabels(maxX.toInt(), scaleX(maxX.toInt()) - 7.dp.toPx(), size.height - padding)
            }
            items.size <= 64 -> {
                drawLabels(1, scaleX(1) - 2.dp.toPx(), size.height - padding)
                drawLabels(
                    (maxX / 2).toInt(),
                    scaleX((maxX / 2).toInt()) - 6.dp.toPx(),
                    size.height - padding
                )
                drawLabels(maxX.toInt(), scaleX(maxX.toInt()) - 7.dp.toPx(), size.height - padding)
            }
            else -> {
                drawLabels(1, scaleX(1) - 2.dp.toPx(), size.height - padding)
                drawLabels(
                    (maxX / 2).toInt(),
                    scaleX((maxX / 2).toInt()) - 6.dp.toPx(),
                    size.height - padding
                )
                drawLabels(maxX.toInt(), scaleX(maxX.toInt()) - 10.dp.toPx(), size.height - padding)
            }
        }

        drawLabels(1, 16f, scaleY(1) - 24)
        drawLabels(5, 13f, scaleY(5) - 24)
        drawLabels(10, 4f, scaleY(10) - 24)

        // Draw data points and lines
        items.forEachIndexed { index, item ->
            val x = scaleX(item.index)
            val y = scaleY(item.points.toInt())
            drawCircle(
                color = Color.Blue,
                radius = 5f,
                center = Offset(x, y)
            )
            if (index > 0) {
                val prevItem = items[index - 1]
                val prevX = scaleX(prevItem.index)
                val prevY = scaleY(prevItem.points.toInt())
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

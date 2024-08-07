package presentation.screen.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.screen.home.ChantedRound

@Composable
fun Chart(items: List<ChantedRound>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val padding = 20.dp.toPx()
        val width = size.width - 2 * padding
        val height = size.height - 2 * padding
        val minX = 1f
        val maxX = 16f
        val minY = 1f
        val maxY = 10f

        fun scaleX(x: Int): Float {
            return padding + (x) / (maxX - minX) * width
        }

        fun scaleY(y: Int): Float {
            return padding + (maxY - y) / (maxY - minY) * height
        }

        // Draw axes
        drawLine(
            color = Color.Black,
            start = Offset(padding, padding),
            end = Offset(padding, size.height - padding),
            strokeWidth = 2f
        )
        drawLine(
            color = Color.Black,
            start = Offset(padding, size.height - padding),
            end = Offset(size.width - padding, size.height - padding),
            strokeWidth = 2f
        )

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



// Draw X and Y axis labels
//        drawContext.canvas.nativeCanvas.drawTex//.nativeCanvas.drawTextLine("1", padding / 2, size.height - padding, paint)
//        drawContext.canvas.nativeCanvas.drawTextBlob().drawText("8", scaleX(8), size.height - padding, paint)
//        drawContext.canvas.nativeCanvas.drawText("16", size.width - padding, size.height - padding, paint)

//        drawContext.canvas.nativeCanvas.drawText("1", padding, size.height - padding + 20, paint)
//        drawContext.canvas.nativeCanvas.drawText("5", padding - 20, scaleY(5), paint)
//        drawContext.canvas.nativeCanvas.drawText("10", padding - 20, scaleY(10), paint)

package com.az.financeapp.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class FinancePieData(
    val label: String,
    val currentValue: Float,
    val fullValue: Float
)

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieView(pieData: FinancePieData = FinancePieData("Today", 100f, 400f)) {
    Row {
        FinancePieChart(pieData.currentValue, pieData.fullValue)
        Column {
            Text(text = pieData.label)
            Text(text = "$${pieData.currentValue}", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieChart(
    currentValue: Float = 1.0f,
    fullValue: Float = 4.0f,
    keyColor: Color = Color.Green
) {
    val fullColor = keyColor.copy(alpha = 0.2f)

    val currentAngle = currentValue / fullValue * 360f

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2
        val center = Offset(canvasSize / 2, canvasSize / 2)
        val stroke = Stroke(18.0f)

        // Draw full value pie
        drawCircle(color = fullColor, radius = radius, center = center, style = stroke)

        // Draw current value pie
        val path = Path().apply {
            moveTo(center.x, center.y - radius) // Move to the top point of the circle
            arcTo(
                rect = Rect(
                    left = center.x - radius,
                    top = center.y - radius,
                    right = center.x + radius,
                    bottom = center.y + radius
                ),
                startAngleDegrees = -90f, // Modify starting angle to -90 degrees (top of the circle)
                sweepAngleDegrees = currentAngle,
                forceMoveTo = false
            )
        }
        drawPath(path = path, color = keyColor, style = stroke)
    }
}
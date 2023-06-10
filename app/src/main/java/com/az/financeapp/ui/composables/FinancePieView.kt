package com.az.financeapp.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
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
fun FinancePieView(pieData: FinancePieData = FinancePieData("Today", 181.39f, 1000f)) {
    Row(verticalAlignment = Alignment.Bottom) {
        FinancePieChart(pieData.currentValue, pieData.fullValue)

        SummaryView(pieData.label, pieData.currentValue)
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun SummaryView(label: String = "Today", currentValue: Float = 181.39f) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.Bottom) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$${currentValue.toInt()}",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = getFloatingPoint(currentValue),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

private fun getFloatingPoint(floatNumber: Float): String {
    val stringNumber = floatNumber.toString()
    val index = stringNumber.indexOf('.')
    return stringNumber.substring(index)
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieChart(
    currentValue: Float = 181.39f,
    fullValue: Float = 1000f,
    keyColor: Color = Color.Green.copy(blue = 0.4f)
) {
    val fullColor = keyColor.copy(alpha = 0.2f)

    val currentAngle = currentValue / fullValue * 360f

    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(currentAngle) {
        animatedProgress.animateTo(
            currentAngle,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Canvas(
        modifier = Modifier
            .size(85.dp)
            .padding(16.dp)
    ) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2
        val center = Offset(canvasSize / 2, canvasSize / 2)

        // Draw full value pie
        drawCircle(color = fullColor, radius = radius, center = center, style = Stroke(25.0f))

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
                sweepAngleDegrees = animatedProgress.value,
                forceMoveTo = false
            )
        }
        drawPath(path = path, color = keyColor, style = Stroke(30.0f))
    }
}
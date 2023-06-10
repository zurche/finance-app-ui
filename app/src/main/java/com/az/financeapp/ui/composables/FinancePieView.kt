package com.az.financeapp.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeapp.ui.theme.DarkPurple
import com.az.financeapp.ui.theme.DarkTeal

data class FinancePieData(
    val label: String,
    val currentValue: Float,
    val fullValue: Float
)

private val mockTodayValue = FinancePieData("Today", 181.39f, 1000f)
private val mockMarchValue = FinancePieData("March", 734.02f, 1000f)

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieRowView(
    pieDataPair: Pair<FinancePieData, FinancePieData> = Pair(
        mockTodayValue,
        mockMarchValue
    )
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        FinancePieView(pieDataPair.first, DarkTeal)

        Divider(
            Modifier
                .width(2.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(4.dp))
                .align(CenterVertically), color = Color(0xffe8eaed)
        )

        FinancePieView(pieDataPair.second, DarkPurple)
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieView(
    pieData: FinancePieData = FinancePieData("Today", 181.39f, 1000f),
    color: Color = DarkTeal
) {
    Row(verticalAlignment = CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        FinancePieChart(pieData.currentValue, pieData.fullValue, color)

        SummaryView(pieData.label, pieData.currentValue)
    }
}

@Composable
@Preview(device = PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun SummaryView(label: String = "Today", currentValue: Float = 181.39f) {
    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.Bottom) {
        Text(text = label, style = MaterialTheme.typography.titleSmall, color = Color.Gray)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$${currentValue.toInt()}",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = getFloatingPoint(currentValue),
                style = MaterialTheme.typography.bodySmall
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
    keyColor: Color = DarkTeal
) {
    val fullColor = keyColor.copy(alpha = 0.3f)

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
            .size(70.dp)
            .padding(16.dp)
    ) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2
        val center = Offset(canvasSize / 2, canvasSize / 2)

        // Draw full value pie
        drawCircle(color = fullColor, radius = radius, center = center, style = Stroke(15.0f))

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
                startAngleDegrees = -110f, // Modify starting angle to -90 degrees (top of the circle)
                sweepAngleDegrees = animatedProgress.value,
                forceMoveTo = false
            )
        }
        drawPath(path = path, color = keyColor, style = Stroke(20.0f))
    }
}
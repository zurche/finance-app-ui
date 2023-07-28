package com.az.financeui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.az.financeui.R
import com.az.financeui.theme.CryptoWhite
import com.az.financeui.theme.LightCarmin
import com.az.financeui.theme.LightOlive

data class AssetInfo(
    val iconDrawable: Int,
    val name: String,
    val tickerName: String,
    val lastDayChange: List<Float>,
    val currentValue: Float,
    val total: Float,
)

private val mockAssetInfo = AssetInfo(
    R.drawable.amd_icon,
    "Advanced Micro Devices, Inc.",
    "AMD",
    listOf(
        113.518f,
        113.799f,
        113.333f,
        113.235f,
        114.099f,
        113.506f,
        113.985f,
        114.212f,
        114.125f,
        113.531f,
        114.228f,
        113.284f,
        114.031f,
        113.493f,
        113.112f
    ),
    113.02211f,
    1356.26f
)

@Composable
@Preview(device = Devices.PIXEL_4_XL)
fun AssetCard(
    assetInfo: AssetInfo = mockAssetInfo
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = CryptoWhite)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AssetIcon(assetInfo.iconDrawable)

            TickerName(assetInfo.name, assetInfo.tickerName)

            PerformanceChartV2(Modifier.size(100.dp), assetInfo.lastDayChange)

            ValueView(assetInfo.currentValue, assetInfo.total)
        }
    }
}

@Composable
fun ValueView(currentValue: Float, total: Float) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = currentValue.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "$${total.toInt()}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}

@Composable
@Preview(heightDp = 300, widthDp = 300, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PerformanceChartV2(modifier: Modifier = Modifier, list: List<Float> = mockAssetInfo.lastDayChange) {
    Row(modifier = modifier) {
        var lastY = 0f
        val max = list.max()
        val min = list.min()
        for (value in list) {
            val yPercentage = (value - min) / (max - min)
            Canvas(modifier = Modifier.fillMaxHeight().weight(1f), onDraw = {
                if (lastY == 0f) { lastY = size.height }
                val currentPoint = Offset(x = size.width, y = size.height.times(1 - yPercentage))
                drawLine(color = Color.Red, start = Offset(x = 0f, y = lastY), end = currentPoint)
                lastY = currentPoint.y
            })
        }
    }
}

@Composable
//@Preview
fun PerformanceChartv1(
    lastDayChange: List<Float> = mockAssetInfo.lastDayChange
) {

    Box(contentAlignment = Alignment.Center) {
        val pairsList = lastDayChange.zipWithNext()
        val offsetIncrement = 24f

        Canvas(modifier = Modifier
            .width(120.dp)
            .height(70.dp)
            .padding(5.dp), onDraw = {

            var lastLineEnd = Offset(0f, size.height)
            var pointDiff: Float
            val lineColor =
                if (lastDayChange.last() > lastDayChange.first()) LightOlive else LightCarmin
            val yModifier = -10f

            for (pair in pairsList) {
                val start = pair.first
                val end = pair.second
                pointDiff = (end - start) * yModifier

                val newLineEnd = Offset(lastLineEnd.x + offsetIncrement, lastLineEnd.y + pointDiff)

                drawLine(
                    color = lineColor,
                    start = lastLineEnd,
                    end = newLineEnd,
                    strokeWidth = 4.0f
                )
                lastLineEnd = newLineEnd
            }
        })
    }

}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PerformanceChart(
    lastDayChange: List<Float> = listOf(102f, 322f, 0f, 150f, 130f),
    maxWidth: Dp = 130.dp,
    maxHeight: Dp = 70.dp
) {

    val augmenterModifierForList = getAugmenterModifierForValue(lastDayChange.max())

    val zippedList = lastDayChange.zipWithNext()

    val lineColor =
        if (lastDayChange.last() > lastDayChange.first()) LightOlive else LightCarmin

    Row(
        modifier = Modifier
            .height(maxHeight)
            .width(maxWidth)
    ) {
        zippedList.forEach {
            Dot(it, lineColor, maxHeight, augmenterModifierForList)
        }
    }

}

private fun getAugmenterModifierForValue(value: Float): Int =
    when (value) {
        in 0f..9f -> 9
        in 9f..99f -> 9_0
        in 99f..999f -> 9_00
        in 999f..9_999f -> 9_000
        in 9_999f..99_999f -> 90_000
        else -> 900_000
    }

@Composable
fun RowScope.Dot(
    value: Pair<Float, Float> = Pair(10f, 30f),
    color: Color = Color.Red,
    maxHeight: Dp = 500.dp,
    graphAugmenterValue: Int = 90
) {
    val firstItemHeight =
        remember(value.first) { value.first * maxHeight.value / graphAugmenterValue }
    val secondItemHeight =
        remember(value.second) { value.second * maxHeight.value / graphAugmenterValue }

    Canvas(modifier = Modifier.weight(1f), onDraw = {
        val firstValueOffset = Offset(x = 0f, y = maxHeight.value - firstItemHeight)
        val secondValueOffset = Offset(x = size.width, y = maxHeight.value - secondItemHeight)

        drawLine(
            color = color,
            start = firstValueOffset,
            end = secondValueOffset,
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
    })
}

@Composable
//@Preview
private fun TickerName(name: String = "Apple Inc.", tickerName: String = "AAPL") {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 5.dp)
            .width(80.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
//@Preview
private fun AssetIcon(iconDrawable: Int = R.drawable.apple) {
    Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier, onDraw = {
            val radius = 65f
            drawCircle(
                color = Color.White,
                radius = radius
            )
        })
        Icon(
            painter = painterResource(id = iconDrawable),
            contentDescription = "Asset Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .padding(bottom = 3.dp)
        )
    }

}

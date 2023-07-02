package com.az.financeui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
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

            PerformanceChart(assetInfo.lastDayChange)

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
//@Preview
fun PerformanceChart(
    lastDayChange: List<Float> = mockAssetInfo.lastDayChange
) {

    Box(contentAlignment = Alignment.Center) {
        val pairsList = lastDayChange.zipWithNext()
        val offsetIncrement = 24f

        Canvas(modifier = Modifier
            .width(120.dp)
            .height(70.dp)
            .padding(5.dp), onDraw = {

            var lastLineEnd = Offset(0f, 140f)
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

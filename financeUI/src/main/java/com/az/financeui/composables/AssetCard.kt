package com.az.financeui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeui.R
import com.az.financeui.theme.CryptoLigthGray

data class AssetInfo(
    val iconDrawable: Int,
    val name: String,
    val tickerName: String,
    val lastDayChange: List<Float>,
    val currentValue: Float,
    val total: Float,
)

@Composable
@Preview(device = Devices.PIXEL_4_XL)
fun AssetCard(
    assetInfo: AssetInfo = AssetInfo(
        R.drawable.apple,
        "Apple Inc.",
        "AAPL",
        listOf(186.06f, 189.82f, 189.39f, 189.83f, 189.97f, 185.02f, 186.07f, 187.03f),
        187.03f,
        1870.3f
    )
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AssetIcon(assetInfo.iconDrawable)

            TickerName(assetInfo.name, assetInfo.tickerName)

            PerformanceChart(assetInfo.lastDayChange)
        }
    }
}

@Composable
@Preview
fun PerformanceChart(
    lastDayChange: List<Float> = listOf(
        186.06f,
        189.82f,
        189.39f,
        189.83f,
        189.97f,
        185.02f,
        186.07f,
        187.03f
    )
) {

    Box(contentAlignment = Alignment.Center) {
        val pairsList = lastDayChange.zipWithNext()
        val offsetIncrement = 10f

        Canvas(modifier = Modifier
            .size(40.dp)
            .padding(5.dp), onDraw = {

            var lastLineEnd = Offset(0f, 0f)
            var pointDiff: Float
            val lineColor =
                if (lastDayChange.last() > lastDayChange.first()) Color.Green else Color.Red

            for (pair in pairsList) {
                val start = pair.first
                val end = pair.second
                pointDiff = end - start

                val newLineEnd = Offset(lastLineEnd.x + offsetIncrement, lastLineEnd.y + pointDiff)

                drawLine(
                    color = lineColor,
                    start = lastLineEnd,
                    end = newLineEnd,
                    strokeWidth = 2.0f
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
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
//@Preview
private fun AssetIcon(iconDrawable: Int = R.drawable.apple) {
    Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier, onDraw = {
            val radius = 50f
            drawCircle(
                color = CryptoLigthGray,
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

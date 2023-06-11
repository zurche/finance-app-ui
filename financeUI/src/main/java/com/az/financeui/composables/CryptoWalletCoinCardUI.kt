package com.az.financeui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeui.R

data class CryptoWalletCoinCardData(
    val name: String,
    val value: Float,
    val valueChange: Int,
    val currentTotal: Long,
    val icon: Int
)

enum class CryptoCardStyle {
    Dark, Light
}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CryptoWalletCoinCardUI(
    style: CryptoCardStyle = CryptoCardStyle.Dark,
    data: CryptoWalletCoinCardData = CryptoWalletCoinCardData(
        name = "Bitcoin",
        icon = R.drawable.ic_btc,
        value = 3.689087f,
        valueChange = -18,
        currentTotal = 98160
    )
) {
    val cardBackground: Color = when (style) {
        CryptoCardStyle.Dark -> Color(0xFF000000)
        CryptoCardStyle.Light -> Color(0xFFadc9ae)
    }

    val textColor: Color = when (style) {
        CryptoCardStyle.Dark -> Color(0xFFFFFFFF)
        CryptoCardStyle.Light -> Color(0xFF000000)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(300.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "${data.valueChange}%",
                    color = textColor,
                    style = MaterialTheme.typography.titleLarge
                )

                ChangeIcon(data, textColor)
            }

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = data.name,
                    color = textColor,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "${data.value}",
                    color = textColor,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "$${data.currentTotal}",
                    color = textColor
                )
            }
        }

    }
}

@Composable
private fun ChangeIcon(
    data: CryptoWalletCoinCardData,
    textColor: Color
) {
    var iconModifier: Modifier = Modifier
    val tint: Color
    val contentDescription: String

    if (data.valueChange > 0) {
        tint = textColor
        contentDescription = "Arrow Up"
    } else {
        tint = Color(0xFFa97d72)
        iconModifier = Modifier.rotate(180f)
        contentDescription = "Arrow Down"
    }

    Icon(
        modifier = iconModifier,
        painter = painterResource(id = R.drawable.ic_arrow_up),
        contentDescription = contentDescription,
        tint = tint
    )
}
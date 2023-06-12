package com.az.financeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeui.R
import com.az.financeui.composables.CryptoCardStyle
import com.az.financeui.composables.CryptoWalletCoinCardData
import com.az.financeui.composables.CryptoWalletCoinCardUI

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CryptoWalletCoinCardPairRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        CryptoWalletCoinCardUI(
            style = CryptoCardStyle.Dark,
            data = CryptoWalletCoinCardData(
                name = "Bitcoin",
                icon = R.drawable.ic_btc,
                value = 3.689087f,
                valueChange = -18,
                currentTotal = 98160
            )
        )

        CryptoWalletCoinCardUI(
            style = CryptoCardStyle.Light,
            data = CryptoWalletCoinCardData(
                name = "Ethereum",
                icon = R.drawable.ic_ethereum,
                value = 94.48096f,
                valueChange = 26,
                currentTotal = 180480
            )
        )
    }

}
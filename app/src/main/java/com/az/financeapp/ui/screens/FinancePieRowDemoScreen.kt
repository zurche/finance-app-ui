package com.az.financeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.az.financeapp.ui.theme.BerryRed
import com.az.financeapp.ui.theme.Blue
import com.az.financeapp.ui.theme.Green
import com.az.financeapp.ui.theme.Orange
import com.az.financeapp.ui.theme.Pine
import com.az.financeapp.ui.theme.Pink
import com.az.financeui.composables.FinancePieData
import com.az.financeui.composables.FinancePieRowView

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FinancePieRowDemoScreen() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Today", 181.39f, 1000f, Pink),
                FinancePieData("March", 734.02f, 1000f, Pine)
            )
        )

        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Entertainment", 5.01f, 300f, Orange),
                FinancePieData("Restaurant", 120.02f, 500f, Blue)
            )
        )

        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Services", 51.01f, 300f, Green),
                FinancePieData("Transport", 220.02f, 500f, BerryRed)
            )
        )
    }
}
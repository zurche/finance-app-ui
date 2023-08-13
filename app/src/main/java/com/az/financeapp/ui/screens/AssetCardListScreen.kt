package com.az.financeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeui.R
import com.az.financeui.composables.AssetInfo
import com.az.financeui.composables.AssetPerformanceCard
import com.az.financeui.composables.AssetsHeader

private val demoAssetList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.apple,
        "Apple Inc.",
        "AAPL",
        listOf(
            182.789f,
            183.235f,
            184.673f,
            183.091f,
            184.987f,
            185.379f,
            186.492f,
            187.091f,
            185.785f,
            188.284f,
            189.982f,
            190.673f,
            191.579f,
            189.284f,
            192.975f
        ),
        187.00023f,
        1870.3f
    ),
    AssetInfo(
        R.drawable.amd_icon,
        "Advanced Micro Devices, Inc.",
        "AMD",
        listOf(
            113.518f,
            112.799f,
            111.333f,
            110.235f,
            111.099f,
            112.506f,
            109.985f,
            108.212f,
            109.125f,
            107.531f,
            106.228f,
            105.284f,
            106.031f,
            109.493f
        ),
        113.02211f,
        1356.26f
    ),
    AssetInfo(
        R.drawable.mongodb_ic,
        "Mongodb Inc.",
        "MDB",
        listOf(
            403.972f,
            401.536f,
            402.241f,
            405.175f,
            402.647f,
            401.829f,
            399.839f,
            398.287f,
            399.671f,
            401.405f,
            397.381f,
            396.093f,
            395.174f,
            392.567f
        ),
        403.00125f,
        3627.011f
    )
)

@Composable
@Preview(showBackground = true)
fun AssetCardListDemoScreen() {
    Column {
        AssetsHeader()

        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {
            items(demoAssetList.size) { index ->
                AssetPerformanceCard(assetInfo = demoAssetList[index])
            }
        })
    }

}
package com.az.financeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeui.R
import com.az.financeui.composables.AssetCard
import com.az.financeui.composables.AssetInfo

private val demoAssetList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.apple,
        "Apple Inc.",
        "AAPL",
        listOf(
            186.789f,
            188.235f,
            185.673f,
            189.091f,
            185.987f,
            185.379f,
            188.492f,
            187.091f,
            184.785f,
            189.284f,
            186.982f,
            187.673f,
            189.579f,
            185.284f,
            184.975f
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
            113.493f
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
            405.536f,
            407.241f,
            408.175f,
            404.647f,
            404.829f,
            407.839f,
            406.287f,
            405.671f,
            404.405f,
            409.381f,
            405.093f,
            404.174f,
            405.567f
        ),
        403.00125f,
        3627.011f
    )
)

@Composable
@Preview
fun AssetCardListDemoScreen() {
    LazyColumn(modifier = Modifier.padding(10.dp), content = {
        items(demoAssetList.size) { index ->
            AssetCard(assetInfo = demoAssetList[index])
        }
    })
}
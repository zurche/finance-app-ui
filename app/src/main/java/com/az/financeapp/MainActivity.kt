package com.az.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.az.financeapp.ui.theme.FinanceAppTheme
import com.az.financeui.composables.FinanceCardVerticalPager
import com.az.financeui.composables.FinancePieRowView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FinancePieRowView()

                    FinanceCardVerticalPager()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinanceAppTheme {
        Greeting("Android")
    }
}
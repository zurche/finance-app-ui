# finance-app-ui

This project is a test mock application with an internal UI library for a Finance App. It provides various UI Composables that can be used to build the user interface of a finance-related application. The library includes the following sections:

- [Pie Row View](#pie-row-view): A Composable for displaying two different categories of budgets with a pie chart and current amount.
- [Plain Pie](#plain-pie): A standalone Composable for displaying the state of a budget with a pie chart.
- [Crypto Card](#crypto-card): A Composable for displaying the current state of a cryptocurrency.

Feel free to explore each section for more details and examples on how to use the UI Composables in your project.

## Pie Row View
![Screenshot 2023-06-11 at 16 52 01](https://github.com/zurche/finance-app-ui/assets/15671525/e6b6cc50-20f7-4bb1-9825-d678363e4c60)

Hey there, ready to slice some pie? The Pie Row View Composable is designed to display two different categories of budgets with three key values:
- Label: Describes the category
- Pie Chart: Represents the current status visually
- Current Amount: Displays the current amount of money for that category

To initialize the Pie Row View, you need to create two `FinancePieData` objects and pass them as a `Pair` to the Composable. The `FinancePieData` class has the following properties:
```kotlin
data class FinancePieData(
    val label: String,
    val currentValue: Float,
    val fullValue: Float,
    val keyColor: Color
)
```

Here's an example of how to use it:
```kotlin
val servicesBudget = FinancePieData("Services", 51.01f, 300f, Green)
val transportBudget = FinancePieData("Transport", 30.50f, 500f, Red)

val pieDataPair = Pair(servicesBudget, transportBudget)

FinancePieRowView(pieDataPair)
```

For more usage examples, refer to the [FinancePieRowDemoScreen](https://github.com/zurche/finance-app-ui/blob/main/app/src/main/java/com/az/financeapp/ui/screens/FinancePieRowDemoScreen.kt) file in this repository.


## Plain Pie
![Screenshot 2023-06-11 at 16 57 20](https://github.com/zurche/finance-app-ui/assets/15671525/e45602a8-9db9-446b-a7dc-ed95786d3887)

Why settle for a plain pie when you can have a scrumptious Plain Pie? The Plain Pie is one of the parts of the Pie Row View. It's used to display the state of the budget and can be used as a standalone Composable.

Here's an example of initialization:
```kotlin
PlainPie(
    keyColor = Pine,
    currentValue = 2.0f,
    fullValue = 13.0f,
    pieSize = 150.dp,
    strokeSize = 40.0f
)
```

The Pie has a small spring animation when the values are loaded into it and the canvas is drawn.

To find out some usage examples, refer to the [PlainPieDemoScreen](https://github.com/zurche/finance-app-ui/blob/main/app/src/main/java/com/az/financeapp/ui/screens/PlainPieDemoScreen.kt).


## Crypto Card
![Screenshot 2023-06-12 at 21 06 30](https://github.com/zurche/finance-app-ui/assets/15671525/e30f1c50-672b-4fb0-a07e-7e8aab5978bf)

Are you ready to dive into the world of crypto? The Crypto Card is a Composable that displays the current state of a cryptocurrency.

To initialize it, a `CryptoWalletCoinCardData` class needs to be passed into it. The class has the following properties:
```kotlin
data class CryptoWalletCoinCardData(
    val name: String,
    val value: Float,
    val valueChange: Int,
    val currentTotal: Long,
    val icon: Int
)
```

On top of this, it supports Light and Dark styling. This needs to be passed as a parameter into the Composable in the `style` parameter.

Here's an example of initialization:
```kotlin
CryptoWalletCoinCardUI(
    style = CryptoCardStyle.Dark,
    data =

 CryptoWalletCoinCardData(
        name = "Bitcoin",
        icon = R.drawable.ic_btc,
        value = 3.689087f,
        valueChange = -18,
        currentTotal = 98160
    )
)
```
![Screenshot 2023-06-12 at 21 11 41](https://github.com/zurche/finance-app-ui/assets/15671525/ca8c26bd-b854-42a9-b18e-88f78d441907)

To find out some usage examples, refer to the [CryptoCardDemoScreen](https://github.com/zurche/finance-app-ui/blob/main/app/src/main/java/com/az/financeapp/ui/screens/CryptoWalletHomeScreen.kt).

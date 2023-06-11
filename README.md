# finance-app-ui
This project is a test mock application with an internal UI library for a Finance App.

## Pie Row View
![PieRowDemoScreen](https://github.com/zurche/finance-app-ui/assets/15671525/f2415450-9405-470b-a6fd-62c6f71e2ea4)

The Pie Row View Composable is designed to display two different categories of budgets with three key values:
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

img

For more usage examples, refer to the [FinancePieRowDemoScreen](https://github.com/zurche/finance-app-ui/blob/main/app/src/main/java/com/az/financeapp/ui/screens/FinancePieRowDemoScreen.kt) file in this repository.
# Plain Pie
[PlainPiePreview.webm](https://github.com/zurche/finance-app-ui/assets/15671525/8c8d658a-a9ab-4972-9019-04b3c426056c)

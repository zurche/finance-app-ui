# finance-app-ui
A test mock project with a ui internal library for a Finance App

# Pie Row View
[img_PieRowDemoScreen (2).webm](https://github.com/zurche/finance-app-ui/assets/15671525/f2415450-9405-470b-a6fd-62c6f71e2ea4)

This Composable is meant to be used to display 2 differeht categories of budgets with 3 key values:
- A Label
- A Pie Chart of the current status
- The money current amount for that category

To initialise it you need to create 2 different `FinancePieData` objects and pass them into the Composable as a `Pair`:
```kotlin
        FinancePieRowView(
            pieDataPair = Pair(
                FinancePieData("Services", 51.01f, 300f, Green),
                FinancePieData("Transport", 220.02f, 500f, BerryRed)
            )
        )
```

Find multiple usages of this view in the file [FinancePieRowDemoScreen]()

# Plain Pie
[PlainPiePreview.webm](https://github.com/zurche/finance-app-ui/assets/15671525/8c8d658a-a9ab-4972-9019-04b3c426056c)

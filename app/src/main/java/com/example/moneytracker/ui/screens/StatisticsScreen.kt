@Composable
fun StatisticsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val statistics by viewModel.statistics.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        StatisticsPeriodSelector()
        Spacer(modifier = Modifier.height(16.dp))
        ExpensesPieChart(statistics.expensesByCategory)
        Spacer(modifier = Modifier.height(16.dp))
        CategoryBreakdown(statistics.expensesByCategory)
    }
}

@Composable
fun StatisticsPeriodSelector() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterChip(
            selected = true,
            onClick = { },
            label = { Text("Week") }
        )
        FilterChip(
            selected = false,
            onClick = { },
            label = { Text("Month") }
        )
        FilterChip(
            selected = false,
            onClick = { },
            label = { Text("Year") }
        )
    }
}
@Composable
fun OverviewScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val balance by viewModel.balance.collectAsState()
    val monthlyExpenses by viewModel.monthlyExpenses.collectAsState()
    val monthlyIncome by viewModel.monthlyIncome.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BalanceCard(balance = balance)
        Spacer(modifier = Modifier.height(16.dp))
        MonthlyOverviewCard(
            expenses = monthlyExpenses,
            income = monthlyIncome
        )
        Spacer(modifier = Modifier.height(16.dp))
        RecentTransactionsList(
            transactions = viewModel.recentTransactions.collectAsState().value
        )
    }
}

@Composable
fun BalanceCard(balance: BigDecimal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Balance",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = balance.formatAsCurrency(),
                style = MaterialTheme.typography.headlineLarge,
                color = if (balance >= BigDecimal.ZERO)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        }
    }
}
@Composable
fun TransactionsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    var showFilterDialog by remember { mutableStateOf(false) }
    val transactions by viewModel.filteredTransactions.collectAsState()

    Column {
        FilterBar(
            onFilterClick = { showFilterDialog = true }
        )

        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(
                    transaction = transaction,
                    onItemClick = { viewModel.selectTransaction(transaction) }
                )
            }
        }
    }

    if (showFilterDialog) {
        FilterDialog(
            onDismiss = { showFilterDialog = false },
            onApplyFilters = { filters -> viewModel.applyFilters(filters) }
        )
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = transaction.category,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = transaction.date.formatForDisplay(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = transaction.amount.formatAsCurrency(),
                style = MaterialTheme.typography.titleMedium,
                color = if (transaction.isExpense())
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.primary
            )
        }
    }
}
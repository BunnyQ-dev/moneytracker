class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val balanceDao: BalanceDao
) {
    fun getTransactionsForUser(userId: Int) = transactionDao.getTransactionsForUser(userId)

    fun getMonthlyExpenses(userId: Int) = transactionDao.getTransactionsByType(userId, TransactionType.EXPENSE)

    fun getMonthlyIncome(userId: Int) = transactionDao.getTransactionsByType(userId, TransactionType.INCOME)

    suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
        updateBalance(transaction)
    }

    private suspend fun updateBalance(transaction: Transaction) {
        val currentBalance = balanceDao.getBalanceForUser(transaction.userId)
        val newAmount = when (transaction.type) {
            TransactionType.INCOME -> currentBalance.balance + transaction.amount
            TransactionType.EXPENSE -> currentBalance.balance - transaction.amount
        }
        balanceDao.updateBalance(currentBalance.copy(balance = newAmount))
    }
}
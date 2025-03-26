data class Transaction(
    val id: Int,
    val userId: Int,
    val type: TransactionType,
    val category: String,
    val amount: Double,
    val date: String
)

enum class TransactionType {
    INCOME, EXPENSE
}


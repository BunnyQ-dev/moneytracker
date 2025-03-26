import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun BigDecimal.formatAsCurrency(): String {
    return "₴%.2f".format(this.setScale(2, RoundingMode.HALF_EVEN))
}

fun LocalDateTime.formatForDisplay(): String {
    return this.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
}

fun Transaction.isExpense(): Boolean = type == TransactionType.EXPENSE
fun Transaction.isIncome(): Boolean = type == TransactionType.INCOME
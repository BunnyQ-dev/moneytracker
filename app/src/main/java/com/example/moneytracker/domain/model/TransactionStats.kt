package com.example.moneytracker.domain.model

import java.math.BigDecimal

data class TransactionStats(
    val totalIncome: BigDecimal,
    val totalExpenses: BigDecimal,
    val expensesByCategory: Map<String, BigDecimal>
) {
    val balance: BigDecimal
        get() = totalIncome - totalExpenses

    val expensePercentageByCategory: Map<String, Double>
        get() {
            if (totalExpenses == BigDecimal.ZERO) return emptyMap()
            return expensesByCategory.mapValues { (_, amount) ->
                (amount.toDouble() / totalExpenses.toDouble()) * 100
            }
        }
}
package com.example.moneytracker.data.models

enum class TransactionType {
    INCOME,
    EXPENSE;

    companion object {
        fun fromString(value: String): TransactionType {
            return when (value.uppercase()) {
                "INCOME" -> INCOME
                "EXPENSE" -> EXPENSE
                else -> throw IllegalArgumentException("Unknown transaction type: $value")
            }
        }
    }
}
package com.example.moneytracker.domain.model

import com.example.moneytracker.data.models.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionWithDetails(
    val id: Int,
    val userId: Int,
    val type: TransactionType,
    val category: String,
    val amount: BigDecimal,
    val date: LocalDateTime,
    val formattedAmount: String,
    val formattedDate: String,
    val categoryIcon: Int
)
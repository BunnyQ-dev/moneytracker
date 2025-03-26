package com.example.moneytracker.domain.usecases.stats

import com.example.moneytracker.data.models.TransactionType
import com.example.moneytracker.domain.model.TransactionStats
import com.example.moneytracker.domain.repository.ITransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class GetTransactionStatsUseCase @Inject constructor(
    private val repository: ITransactionRepository
) {
    operator fun invoke(
        userId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<TransactionStats> {
        return repository.getTransactionsBetweenDates(userId, startDate, endDate)
            .map { transactions ->
                TransactionStats(
                    totalIncome = transactions
                        .filter { it.type == TransactionType.INCOME }
                        .sumOf { it.amount },
                    totalExpenses = transactions
                        .filter { it.type == TransactionType.EXPENSE }
                        .sumOf { it.amount },
                    expensesByCategory = transactions
                        .filter { it.type == TransactionType.EXPENSE }
                        .groupBy { it.category }
                        .mapValues { (_, transactions) ->
                            transactions.sumOf { it.amount }
                        }
                )
            }
    }
}
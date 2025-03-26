package com.example.moneytracker.domain.repository

import com.example.moneytracker.data.models.Transaction
import com.example.moneytracker.data.models.TransactionType
import com.example.moneytracker.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ITransactionRepository {
    fun getAllTransactions(userId: Int): Flow<List<Transaction>>
    fun getTransactionsByType(userId: Int, type: TransactionType): Flow<List<Transaction>>
    fun getTransactionsBetweenDates(
        userId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction): Result<Unit>
    suspend fun updateTransaction(transaction: Transaction): Result<Unit>
    suspend fun deleteTransaction(transaction: Transaction): Result<Unit>
    suspend fun getTransactionById(id: Int): Result<Transaction>
}
package com.example.moneytracker.domain.usecases.transaction

import Transaction
import com.example.moneytracker.domain.model.Result
import com.example.moneytracker.domain.repository.IBalanceRepository
import com.example.moneytracker.domain.repository.ITransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionRepository: ITransactionRepository,
    private val balanceRepository: IBalanceRepository
) {
    suspend operator fun invoke(transaction: Transaction): Result<Unit> {
        return try {
            transactionRepository.addTransaction(transaction)
            balanceRepository.updateBalanceForTransaction(transaction)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
package com.example.moneytracker.domain.usecases.transaction

import com.example.moneytracker.data.models.Transaction
import com.example.moneytracker.domain.model.Result
import com.example.moneytracker.domain.repository.IBalanceRepository
import com.example.moneytracker.domain.repository.ITransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val transactionRepository: ITransactionRepository,
    private val balanceRepository: IBalanceRepository
) {
    suspend operator fun invoke(transaction: Transaction): Result<Unit> {
        return try {
            transactionRepository.deleteTransaction(transaction)
            balanceRepository.revertBalanceForTransaction(transaction)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
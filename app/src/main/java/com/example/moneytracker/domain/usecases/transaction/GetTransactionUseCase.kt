package com.example.moneytracker.domain.usecases.transaction

import com.example.moneytracker.data.models.Transaction
import com.example.moneytracker.domain.repository.ITransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val repository: ITransactionRepository
) {
    operator fun invoke(userId: Int): Flow<List<Transaction>> {
        return repository.getAllTransactions(userId)
    }
}
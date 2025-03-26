package com.example.moneytracker.domain.usecases.balance

import com.example.moneytracker.data.models.Balance
import com.example.moneytracker.domain.repository.IBalanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val repository: IBalanceRepository
) {
    operator fun invoke(userId: Int): Flow<Balance> {
        return repository.getBalance(userId)
    }
}
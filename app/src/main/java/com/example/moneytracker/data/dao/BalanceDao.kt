import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balance WHERE user_id = :userId")
    fun getBalanceForUser(userId: Int): Flow<Balance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: Balance)

    @Update
    suspend fun updateBalance(balance: Balance)

    @Query("UPDATE balance SET balance = balance + :amount WHERE user_id = :userId")
    suspend fun addToBalance(userId: Int, amount: Double)

    @Query("UPDATE balance SET balance = balance - :amount WHERE user_id = :userId")
    suspend fun subtractFromBalance(userId: Int, amount: Double)

    @Query("SELECT balance FROM balance WHERE user_id = :userId")
    suspend fun getCurrentBalance(userId: Int): Double?

    @Transaction
    suspend fun initializeBalanceForUser(userId: Int) {
        val existingBalance = getCurrentBalance(userId)
        if (existingBalance == null) {
            insertBalance(Balance(0, userId, 0.0))
        }
    }
}
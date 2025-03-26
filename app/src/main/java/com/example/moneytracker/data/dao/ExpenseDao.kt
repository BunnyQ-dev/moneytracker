import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses WHERE user_id = :userId ORDER BY date DESC")
    fun getAllExpensesForUser(userId: Int): Flow<List<Expense>>

    @Query("""
        SELECT * FROM expenses 
        WHERE user_id = :userId 
        AND date BETWEEN :startDate AND :endDate 
        ORDER BY date DESC
    """)
    fun getExpensesForPeriod(userId: Int, startDate: String, endDate: String): Flow<List<Expense>>

    @Query("""
        SELECT category, SUM(amount) as total 
        FROM expenses 
        WHERE user_id = :userId 
        AND date BETWEEN :startDate AND :endDate 
        GROUP BY category
    """)
    fun getExpensesByCategory(userId: Int, startDate: String, endDate: String): Flow<Map<String, Double>>

    @Query("SELECT SUM(amount) FROM expenses WHERE user_id = :userId AND date BETWEEN :startDate AND :endDate")
    fun getTotalExpensesForPeriod(userId: Int, startDate: String, endDate: String): Flow<Double?>

    @Insert
    suspend fun insertExpense(expense: Expense): Long

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("""
        SELECT SUM(amount) 
        FROM expenses 
        WHERE user_id = :userId 
        AND strftime('%Y-%m', date) = strftime('%Y-%m', 'now')
    """)
    fun getCurrentMonthExpenses(userId: Int): Flow<Double?>
}
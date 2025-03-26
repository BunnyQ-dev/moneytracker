import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Query("SELECT * FROM income WHERE user_id = :userId ORDER BY date DESC")
    fun getAllIncomeForUser(userId: Int): Flow<List<Income>>

    @Query("""
        SELECT * FROM income 
        WHERE user_id = :userId 
        AND date BETWEEN :startDate AND :endDate 
        ORDER BY date DESC
    """)
    fun getIncomeForPeriod(userId: Int, startDate: String, endDate: String): Flow<List<Income>>

    @Query("""
        SELECT category, SUM(amount) as total 
        FROM income 
        WHERE user_id = :userId 
        AND date BETWEEN :startDate AND :endDate 
        GROUP BY category
    """)
    fun getIncomeByCategory(userId: Int, startDate: String, endDate: String): Flow<Map<String, Double>>

    @Query("SELECT SUM(amount) FROM income WHERE user_id = :userId AND date BETWEEN :startDate AND :endDate")
    fun getTotalIncomeForPeriod(userId: Int, startDate: String, endDate: String): Flow<Double?>

    @Insert
    suspend fun insertIncome(income: Income): Long

    @Update
    suspend fun updateIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("""
        SELECT SUM(amount) 
        FROM income 
        WHERE user_id = :userId 
        AND strftime('%Y-%m', date) = strftime('%Y-%m', 'now')
    """)
    fun getCurrentMonthIncome(userId: Int): Flow<Double?>
}
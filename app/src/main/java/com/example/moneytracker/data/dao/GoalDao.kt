package com.example.moneytracker.data.models
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals WHERE user_id = :userId")
    fun getAllGoalsForUser(userId: Int): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE user_id = :userId AND is_finished = 0")
    fun getActiveGoalsForUser(userId: Int): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :goalId AND user_id = :userId")
    fun getGoalById(goalId: Int, userId: Int): Flow<Goal?>

    @Insert
    suspend fun insertGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("UPDATE goals SET saved_amount = saved_amount + :amount WHERE id = :goalId")
    suspend fun addToGoalSavings(goalId: Int, amount: Double)

    @Query("""
        UPDATE goals 
        SET is_finished = CASE 
            WHEN saved_amount >= target_amount THEN 1 
            ELSE is_finished 
        END 
        WHERE id = :goalId
    """)
    suspend fun checkGoalCompletion(goalId: Int)

    @Transaction
    suspend fun addSavingsToGoal(goalId: Int, amount: Double) {
        addToGoalSavings(goalId, amount)
        checkGoalCompletion(goalId)
    }
}
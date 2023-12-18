package nebraszka.selfaid.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.local.entities.ExerciseEntity

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM TB_EJ_Exercises ORDER BY exercise_type ")
    fun getAllExercises(): Flow<List<ExerciseEntity>>
}
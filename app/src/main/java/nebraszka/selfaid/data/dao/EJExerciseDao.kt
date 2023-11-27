package nebraszka.selfaid.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.entities.EJExercise

@Dao
interface EJExerciseDao {
    @Query("SELECT * FROM TB_EJ_Exercises ORDER BY exercise_type ")
    fun getAllExercises(): Flow<List<EJExercise>>
}
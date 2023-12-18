package nebraszka.selfaid.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.local.entities.AnswerSuggestionEntity

@Dao
interface AnswerSuggestionDao {
    @Query("SELECT * FROM TB_Answer_Suggest WHERE question_id == :questionId")
    fun getAllAnswerSuggestions(questionId: Int): Flow<List<AnswerSuggestionEntity>>
}
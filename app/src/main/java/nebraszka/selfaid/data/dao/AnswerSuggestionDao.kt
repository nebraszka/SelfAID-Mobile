package nebraszka.selfaid.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.entities.AnswerSuggestion

@Dao
interface AnswerSuggestionDao {
    @Query("SELECT * FROM TB_Answer_Suggest WHERE question_id == :questionId")
    fun getAllAnswerSuggestions(questionId: Int): Flow<List<AnswerSuggestion>>
}
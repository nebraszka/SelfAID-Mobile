package nebraszka.selfaid.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.entities.Emotion

@Dao
interface EmotionDao {

    @Query("SELECT * FROM TB_Emotions ORDER BY emotion ASC")
    fun getAlphabetizedEmotions(): Flow<List<Emotion>>

    @Query("SELECT * FROM TB_Emotions WHERE emotion == :name")
    fun getEmotion(name: String): Flow<Emotion>

}
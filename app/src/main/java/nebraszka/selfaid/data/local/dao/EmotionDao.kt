package nebraszka.selfaid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.entities.EmotionEntity

@Dao
interface EmotionDao {

    @Query("SELECT * FROM TB_Emotions ORDER BY emotion ASC")
    fun getAlphabetizedEmotions(): Flow<List<EmotionEntity>>

    @Query("SELECT * FROM TB_Emotions WHERE emotion == :name")
    fun getEmotion(name: String): Flow<EmotionEntity>

    // TODO suspend?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmotions(emotions: List<EmotionEntity>)
}
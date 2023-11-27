package nebraszka.selfaid.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.entities.Emotion
import nebraszka.selfaid.data.entities.EntryPage

@Dao
interface EntryPageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPage(page: EntryPage): Long

    @Query(
        "SELECT emotions.* FROM TB_Entry_Pages AS pages " +
                "INNER JOIN TB_Emotions AS emotions " +
                "ON pages.emotion_id=emotions.id " +
                "WHERE pages.id=:pageId"
    )
    fun getEmotion(pageId: Int): Flow<Emotion>
}
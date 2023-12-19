package nebraszka.selfaid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.EmotionEntity

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: EntryEntity): Long

    @Query("SELECT * FROM TB_EJ_Entries WHERE id=:entryId")
    fun getEntry(entryId: Int): Flow<EntryEntity>

    @Query("SELECT * FROM TB_EJ_Entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<EntryEntity>>

    @Query("SELECT COUNT(*) FROM TB_EJ_Entries")
    fun countEntries(): Flow<Int>

    @Query(
        "SELECT pagesWithEmotionInfo.emotion AS id," +
                "pagesWithEmotionInfo.emotion, pagesWithEmotionInfo.description " +
                "FROM TB_EJ_Entries AS entries " +
                "INNER JOIN (" +
                "   SELECT * FROM TB_Entry_Pages AS pages" +
                "   INNER JOIN TB_Emotions AS emotions" +
                "       ON pages.emotion=emotions.emotion" +
                ") AS pagesWithEmotionInfo " +
                "   ON pagesWithEmotionInfo.entry_id=entries.id " +
                "WHERE entries.id=:entryId"
    )
    fun getEntryEmotion(entryId: Int): Flow<EmotionEntity>

    @Query("DELETE FROM TB_EJ_Entries WHERE id = :entryId")
    suspend fun deleteByEntryId(entryId: Int)
}
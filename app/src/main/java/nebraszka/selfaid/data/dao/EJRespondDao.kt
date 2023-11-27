package nebraszka.selfaid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.entities.EJAnswer
import nebraszka.selfaid.data.entities.EJRespond

@Dao
interface EJRespondDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    suspend fun insertAllResponds(responds: List<EJRespond>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRespond(respond: EJRespond)

    @Query(
        "SELECT responds.text_answer, suggest.answer " +
                "FROM (SELECT  text_answer, chosen_answer " +
                "       FROM TB_EJ_Responds AS responds " +
                "       INNER JOIN " +
                "       (SELECT * FROM TB_Entry_Pages AS pages " +
                "             INNER JOIN TB_EJ_Entries AS entries " +
                "                ON pages.entry_id=entries.id ) AS pagesAndEntries " +
                "       ON responds.page_id=pagesAndEntries.id " +
                "       WHERE pagesAndEntries.page_number=:page AND " +
                "       pagesAndEntries.entry_id=:entryId AND " +
                "       responds.exercise_id=:exerciseId) AS responds " +
                "LEFT JOIN TB_Answer_Suggest AS suggest " +
                "ON responds.chosen_answer=suggest.id"
    )
    fun getRespondsForExercise(entryId: Int, page: Int, exerciseId: Int): Flow<List<EJAnswer>>
}

package nebraszka.selfaid.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.dao.*
import nebraszka.selfaid.data.entities.*

class SelfAIDRepository(
    private val emotionDao: EmotionDao,
    private val ejExerciseDao: EJExerciseDao,
    private val ejEntryDao: EJEntryDao,
    private val entryPageDao: EntryPageDao,
    private val ejRespondDao: EJRespondDao
) {
    val allEntries: Flow<List<Entry>> = ejEntryDao.getAllEntries()
    val allEmotions: Flow<List<Emotion>> = emotionDao.getAlphabetizedEmotions()
    val allExercises: Flow<List<Exercise>> = ejExerciseDao.getAllExercises()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getEmotion(name: String): Flow<Emotion> {
        return emotionDao.getEmotion(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteByEntryId(entryId: Int){
        return ejEntryDao.deleteByEntryId(entryId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertEntryPage(page: EntryPage): Long {
        return entryPageDao.insertPage(page)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertEntry(entry: Entry): Long {
        return ejEntryDao.insert(entry)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getEntryInfo(entryId: Int): Flow<Entry> {
        return ejEntryDao.getEntry(entryId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getEntryEmotion(entryId: Int): Flow<Emotion> {
        return ejEntryDao.getEntryEmotion(entryId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertResponds(responds: List<Respond>) {
        ejRespondDao.insertAllResponds(responds)
    }
}

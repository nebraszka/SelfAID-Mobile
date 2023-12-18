package nebraszka.selfaid.data.local

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.dao.*
import nebraszka.selfaid.data.local.entities.*

class SelfAIDLocalRepository(
    private val emotionDao: EmotionDao,
    private val exerciseDao: ExerciseDao,
    private val entryDao: EntryDao,
    private val entryPageDao: EntryPageDao,
    private val respondDao: RespondDao
) {
    val allEntries: Flow<List<EntryEntity>> = entryDao.getAllEntries()
    val allEmotions: Flow<List<Emotion>> = emotionDao.getAlphabetizedEmotions().map { emotionMapper.mapToDomainModelList(it) }
    val allExercises: Flow<List<ExerciseEntity>> = exerciseDao.getAllExercises()

    private val emotionMapper = EmotionEntityMapper()
    //TODO: RedundantSuspendModifier

    @WorkerThread
    fun getEmotion(name: String): Flow<Emotion> {
        return emotionDao.getEmotion(name).map { emotionMapper.mapToDomainModel(it) }
    }


    @WorkerThread
    suspend fun deleteByEntryId(entryId: Int){
        return entryDao.deleteByEntryId(entryId)
    }

    @WorkerThread
    suspend fun insertEntryPage(page: EntryPageEntity): Long {
        return entryPageDao.insertPage(page)
    }

    @WorkerThread
    suspend fun insertEntry(entry: EntryEntity): Long {
        return entryDao.insert(entry)
    }

    @WorkerThread
    fun getEntryInfo(entryId: Int): Flow<EntryEntity> {
        return entryDao.getEntry(entryId)
    }

    @WorkerThread
    fun getEntryEmotion(entryId: Int): Flow<EmotionEntity> {
        return entryDao.getEntryEmotion(entryId)
    }

    @WorkerThread
    suspend fun insertResponds(responds: List<RespondEntity>) {
        respondDao.insertAllResponds(responds)
    }
}

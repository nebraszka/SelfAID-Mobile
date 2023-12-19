package nebraszka.selfaid.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.dao.*
import nebraszka.selfaid.data.local.entities.*
import nebraszka.selfaid.data.network.SelfAIDNetworkDataSource
import nebraszka.selfaid.data.network.models.EmotionDtoMapper

class SelfAIDRepositoryImpl(
    private val emotionDao: EmotionDao,
    private val exerciseDao: ExerciseDao,
    private val entryDao: EntryDao,
    private val entryPageDao: EntryPageDao,
    private val respondDao: RespondDao,
    private val network: SelfAIDNetworkDataSource
) {

    // TODO: change to functions
    val allEntries: Flow<List<EntryEntity>> = entryDao.getAllEntries()
    val allExercises: Flow<List<ExerciseEntity>> = exerciseDao.getAllExercises()

    private val emotionEntityMapper = EmotionEntityMapper()
    private val emotionDtoMapper = EmotionDtoMapper()
    //TODO: RedundantSuspendModifier ???

    suspend fun getAllEmotions(): Flow<List<Emotion>> = flow {
        val result = withContext(Dispatchers.IO) {
            network.getAllEmotions()
        }
        if (result.isSuccessful) {
            result.body()?.let { responseBody ->
                if (responseBody.success) {
                    withContext(Dispatchers.IO) {
                        emotionDao.insertEmotions(emotionDtoMapper.mapToEntityModelList(responseBody.data))
                    }
                }
            }
        }
        emitAll(emotionDao.getAlphabetizedEmotions()
            .map { emotionEntityMapper.mapToDomainModelList(it) })
    }


    @WorkerThread
    fun getEmotion(name: String): Flow<Emotion> {
        return emotionDao.getEmotion(name).map { emotionEntityMapper.mapToDomainModel(it) }
    }

    // TODO: DELETE, its for debug
//    @WorkerThread
//    suspend fun deleteAllEmotions(){
//        network.deleteAllEmotions()
//    }


    @WorkerThread
    suspend fun deleteByEntryId(entryId: Int) {
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
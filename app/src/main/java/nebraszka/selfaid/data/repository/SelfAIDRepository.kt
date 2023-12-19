package nebraszka.selfaid.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import nebraszka.selfaid.data.domain.model.*
import nebraszka.selfaid.data.local.entities.*

interface SelfAIDRepository {

    @WorkerThread
    fun getAllEmotions(): Flow<List<Emotion>>

    @WorkerThread
    fun getEmotion(name: String): Flow<Emotion>

    @WorkerThread
    suspend fun deleteByEntryId(entryId: Int)

    @WorkerThread
    suspend fun insertEntryPage(page: EntryPageEntity): Long

    @WorkerThread
    suspend fun insertEntry(entry: EntryEntity): Long

    @WorkerThread
    fun getEntryInfo(entryId: Int): Flow<EntryEntity>

    @WorkerThread
    fun getEntryEmotion(entryId: Int): Flow<EmotionEntity>

    @WorkerThread
    suspend fun insertResponds(responds: List<RespondEntity>)
}
package nebraszka.selfaid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.data.local.dao.EmotionDao
import nebraszka.selfaid.data.local.dao.EntryDao
import nebraszka.selfaid.data.local.dao.EntryPageDao
import nebraszka.selfaid.data.local.dao.ExerciseDao
import nebraszka.selfaid.data.local.dao.RespondDao
import nebraszka.selfaid.data.network.models.EmotionDtoMapper
import nebraszka.selfaid.data.repository.SelfAIDRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSelfAIDRepository(
        emotionDao: EmotionDao,
        exerciseDao: ExerciseDao,
        entryDao: EntryDao,
        entryPageDao: EntryPageDao,
        respondDao: RespondDao
    ): SelfAIDRepository {
        return SelfAIDRepository(
            emotionDao = emotionDao,
            exerciseDao = exerciseDao,
            entryDao = entryDao,
            entryPageDao = entryPageDao,
            respondDao = respondDao,
            network = null
        )
    }
}
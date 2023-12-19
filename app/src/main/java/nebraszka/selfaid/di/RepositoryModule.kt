package nebraszka.selfaid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.data.local.dao.*
import nebraszka.selfaid.data.network.SelfAIDNetworkDataSource
import nebraszka.selfaid.data.repository.*
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
        respondDao: RespondDao,
        network: SelfAIDNetworkDataSource
    ): SelfAIDRepositoryImpl { // TODO change to SelfAIDRepository -> when all* vals disappears
        return SelfAIDRepositoryImpl(
            emotionDao = emotionDao,
            exerciseDao = exerciseDao,
            entryDao = entryDao,
            entryPageDao = entryPageDao,
            respondDao = respondDao,
            network = network
        )
    }
}
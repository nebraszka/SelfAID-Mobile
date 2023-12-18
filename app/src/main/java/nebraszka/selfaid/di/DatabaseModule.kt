package nebraszka.selfaid.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.data.local.SelfAIDDatabase
import nebraszka.selfaid.data.local.dao.EmotionDao
import nebraszka.selfaid.data.local.dao.EntryDao
import nebraszka.selfaid.data.local.dao.EntryPageDao
import nebraszka.selfaid.data.local.dao.ExerciseDao
import nebraszka.selfaid.data.local.dao.RespondDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SelfAIDDatabase {
        return SelfAIDDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideEmotionDao(database: SelfAIDDatabase): EmotionDao {
        return database.emotionDao()
    }

    @Singleton
    @Provides
    fun provideExerciseDao(database: SelfAIDDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Singleton
    @Provides
    fun provideEntryDao(database: SelfAIDDatabase): EntryDao {
        return database.entryDao()
    }

    @Singleton
    @Provides
    fun provideEntryPageDao(database: SelfAIDDatabase): EntryPageDao {
        return database.entryPageDao()
    }

    @Singleton
    @Provides
    fun provideRespondDao(database: SelfAIDDatabase): RespondDao {
        return database.respondDao()
    }

}

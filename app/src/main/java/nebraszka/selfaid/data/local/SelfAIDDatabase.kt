package nebraszka.selfaid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nebraszka.selfaid.R
import nebraszka.selfaid.data.local.dao.AnswerSuggestionDao
import nebraszka.selfaid.data.local.dao.EntryDao
import nebraszka.selfaid.data.local.dao.ExerciseDao
import nebraszka.selfaid.data.local.dao.RespondDao
import nebraszka.selfaid.data.local.dao.EmotionDao
import nebraszka.selfaid.data.local.dao.EntryPageDao
import nebraszka.selfaid.data.local.entities.AnswerSuggestionEntity
import nebraszka.selfaid.data.local.entities.EmotionEntity
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.EntryPageEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.data.local.entities.ExerciseTypeEntity
import nebraszka.selfaid.data.local.entities.RespondEntity

@Database(
    entities = [
        EmotionEntity::class,
        ExerciseEntity::class,
        ExerciseTypeEntity::class,
        AnswerSuggestionEntity::class,
        EntryEntity::class,
        EntryPageEntity::class,
        RespondEntity::class
    ], version = 15, exportSchema = false // TODO: Do zmiany potem
)
abstract class SelfAIDDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun answerSuggestionDao(): AnswerSuggestionDao
    abstract fun entryDao(): EntryDao
    abstract fun entryPageDao(): EntryPageDao
    abstract fun respondDao(): RespondDao

    companion object {

        @Volatile
        private var INSTANCE: SelfAIDDatabase? = null

        fun getDatabase(context: Context): SelfAIDDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SelfAIDDatabase::class.java,
                    context.resources.getString(R.string.database_name)
                )
                    .createFromAsset(context.resources.getString(R.string.database_path))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
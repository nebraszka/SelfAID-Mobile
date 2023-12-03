package nebraszka.selfaid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nebraszka.selfaid.R
import nebraszka.selfaid.data.dao.*
import nebraszka.selfaid.data.entities.*
@Database(
    entities = [
        Emotion::class,
        Exercise::class,
        ExerciseType::class,
        AnswerSuggestion::class,
        Entry::class,
        EntryPage::class,
        Respond::class
    ], version = 13, exportSchema = false // TODO: Do zmiany potem
)
abstract class SelfAIDDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao
    abstract fun exerciseDao(): EJExerciseDao
    abstract fun answerSuggestionDao(): AnswerSuggestionDao
    abstract fun ejEntryDao(): EJEntryDao
    abstract fun entryPageDao(): EntryPageDao
    abstract fun ejRespondDao(): EJRespondDao

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
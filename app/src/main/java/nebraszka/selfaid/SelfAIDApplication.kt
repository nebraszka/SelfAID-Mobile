package nebraszka.selfaid

import android.app.Application
import nebraszka.selfaid.data.SelfAIDDatabase
import nebraszka.selfaid.data.SelfAIDRepository

class SelfAIDApplication: Application() {
    val database by lazy { SelfAIDDatabase.getDatabase(this) }

    val repository by lazy {
        SelfAIDRepository(
            database.emotionDao(),
            database.exerciseDao(),
            database.ejEntryDao(),
            database.entryPageDao(),
            database.ejRespondDao()
        )
    }
}
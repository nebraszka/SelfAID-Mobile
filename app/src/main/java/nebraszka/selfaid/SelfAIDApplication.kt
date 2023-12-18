package nebraszka.selfaid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import nebraszka.selfaid.data.local.SelfAIDDatabase
import nebraszka.selfaid.data.repository.SelfAIDRepository

//import nebraszka.selfaid.data.network.SelfAIDApiService
//import nebraszka.selfaid.data.network.retrofit

@HiltAndroidApp
class SelfAIDApplication : Application() {

    val database by lazy { SelfAIDDatabase.getDatabase(this) }

    val repository by lazy {
        SelfAIDRepository(
            database.emotionDao(),
            database.exerciseDao(),
            database.entryDao(),
            database.entryPageDao(),
            database.respondDao()
        )
    }
}

//    val retrofitService: SelfAIDApiService by lazy {
//        retrofit.create(SelfAIDApiService::class.java)
//    }


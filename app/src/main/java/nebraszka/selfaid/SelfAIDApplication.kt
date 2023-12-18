package nebraszka.selfaid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import nebraszka.selfaid.data.local.SelfAIDDatabase
import nebraszka.selfaid.data.repository.SelfAIDRepository

//import nebraszka.selfaid.data.network.SelfAIDApiService
//import nebraszka.selfaid.data.network.retrofit

@HiltAndroidApp
class SelfAIDApplication : Application() {

}

//    val retrofitService: SelfAIDApiService by lazy {
//        retrofit.create(SelfAIDApiService::class.java)
//    }


package nebraszka.selfaid.data.network

import nebraszka.selfaid.data.network.responses.AllEmotionGetResponse
import nebraszka.selfaid.data.network.responses.EmotionGetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.DELETE

interface SelfAIDNetworkDataSource {
    @GET("Emotion")
    suspend fun getAllEmotions(): Response<AllEmotionGetResponse>

    @GET("Emotion/{id}")
    suspend fun getEmotionById(id: Int): Response<EmotionGetResponse>

    @DELETE("Emotion")
    suspend fun deleteAllEmotions()
}

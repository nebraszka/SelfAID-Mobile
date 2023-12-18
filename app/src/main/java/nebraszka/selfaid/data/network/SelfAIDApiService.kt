package nebraszka.selfaid.data.network

import nebraszka.selfaid.data.network.response.AllEmotionGetResponse
import retrofit2.http.GET
import retrofit2.http.DELETE

private const val BASE_URL = "http://10.0.2.2:5257/api/";

interface EmotionApiService {
    @GET("Emotion")
    suspend fun getAllEmotions(): AllEmotionGetResponse

    @DELETE("Emotion")
    suspend fun deleteAllEmotions()
}

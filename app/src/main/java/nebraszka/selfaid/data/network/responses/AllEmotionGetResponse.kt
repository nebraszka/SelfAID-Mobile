package nebraszka.selfaid.data.network.responses

import com.google.gson.annotations.SerializedName
import nebraszka.selfaid.data.network.models.EmotionDto

data class AllEmotionGetResponse (
    @SerializedName("data") val data : List<EmotionDto>,
    @SerializedName("success") val success : Boolean,
    @SerializedName("message") val message : String
)
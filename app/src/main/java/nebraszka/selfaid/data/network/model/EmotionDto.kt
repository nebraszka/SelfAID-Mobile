package nebraszka.selfaid.data.network.model

import com.google.gson.annotations.SerializedName
data class EmotionDto (
    @SerializedName("id") val id : Int,
    @SerializedName("emotion") val emotion : String,
    @SerializedName("description") val description : String,
)
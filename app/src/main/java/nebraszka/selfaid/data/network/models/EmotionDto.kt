package nebraszka.selfaid.data.network.models

import com.google.gson.annotations.SerializedName
data class EmotionDto (
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
)
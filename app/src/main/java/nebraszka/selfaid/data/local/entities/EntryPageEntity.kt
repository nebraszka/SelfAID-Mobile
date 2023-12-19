package nebraszka.selfaid.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_Entry_Pages",
    foreignKeys = [ForeignKey(
        entity = EmotionEntity::class,
        parentColumns = arrayOf("emotion"),
        childColumns = arrayOf("emotion"),
        onDelete = CASCADE
    ), ForeignKey(
        entity = EntryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("entry_id"),
        onDelete = CASCADE
    )]
)
data class EntryPageEntity(
    @NonNull
    @ColumnInfo(name = "page_number", index = true) val pageNo: Int,
    @NonNull
    @ColumnInfo(name = "emotion") val emotion: String,
    @NonNull
    @ColumnInfo(name = "entry_id", index = true) var entryId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
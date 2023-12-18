package nebraszka.selfaid.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_EJ_Entries")
data class EntryEntity(
    @NonNull
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "title") var title: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
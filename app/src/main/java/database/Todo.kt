package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class Todo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int?,
        @ColumnInfo(name = "todo") val text: String?
)
package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query(value = "SELECT id, todo FROM todo_list")
    fun getAll(): List<Todo>

    @Insert
    fun insert(value: Todo)

    @Query(value = "DELETE FROM todo_list")
    fun delete()
}
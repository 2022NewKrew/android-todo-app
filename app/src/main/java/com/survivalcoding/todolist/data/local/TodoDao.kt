package com.survivalcoding.todolist.data.local

import androidx.room.*
import com.survivalcoding.todolist.domain.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getTodoList(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(todo: Todo)

    @Update
    suspend fun updateItem(todo: Todo)

    @Delete
    suspend fun deleteItem(todo: Todo)

    @Query("SELECT * FROM todo WHERE title LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<Todo>
}
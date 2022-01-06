package com.survivalcoding.todolist.data

import androidx.room.*
import com.survivalcoding.todolist.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun selectAll(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}

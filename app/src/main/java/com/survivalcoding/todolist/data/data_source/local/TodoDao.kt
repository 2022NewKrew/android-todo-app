package com.survivalcoding.todolist.data.data_source.local

import androidx.room.*
import com.survivalcoding.todolist.domain.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getTodos(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Update
    suspend fun update(todo: Todo)
}
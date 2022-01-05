package com.survivalcoding.todolist.data.data_source.local

import androidx.room.*
import com.survivalcoding.todolist.domain.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getTodos(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun update(todo: Todo)
}
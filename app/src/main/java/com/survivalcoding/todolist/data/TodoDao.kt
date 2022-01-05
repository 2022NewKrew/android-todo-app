package com.survivalcoding.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO_TB")
    fun getAll(): Flow<List<TodoModel>>

    @Update
    fun updateTodo(todo: TodoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo: TodoModel)

    @Delete
    fun delete(todo: TodoModel)

    @Query("SELECT * FROM TODO_TB WHERE title = :title COLLATE NOCASE")
    fun search(title: String): LiveData<List<TodoModel>>

}
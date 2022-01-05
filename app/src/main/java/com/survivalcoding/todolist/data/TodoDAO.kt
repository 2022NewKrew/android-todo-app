package com.survivalcoding.todolist.data

import androidx.room.*
import com.survivalcoding.todolist.domain.entity.Todo


@Dao
interface TodoDAO {
    @Query("SELECT * FROM TODO_TB")
    fun getAll(): List<Todo>

    @Update
    fun updateTodo(todo: Todo)

    @Insert
    fun  insertTodos(vararg: Todo)

    @Delete
    fun delete(todo: Todo)
}
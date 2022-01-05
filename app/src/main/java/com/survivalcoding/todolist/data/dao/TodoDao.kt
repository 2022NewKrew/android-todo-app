package com.survivalcoding.todolist.data.dao

import androidx.room.*
import com.survivalcoding.todolist.domain.models.TodoItem


@Dao
interface TodoDao {
    @Query("SELECT * from TodoItem")
    fun selectAll(): List<TodoItem>

    @Query("SELECT * from TodoItem WHERE id = :id LIMIT 1")
    fun getById(id: Long): TodoItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TodoItem)

    @Update
    fun update(item: TodoItem)
}
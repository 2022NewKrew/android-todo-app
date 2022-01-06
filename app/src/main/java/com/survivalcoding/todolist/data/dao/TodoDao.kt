package com.survivalcoding.todolist.data.dao

import androidx.room.*
import com.survivalcoding.todolist.domain.models.TodoItem


@Dao
interface TodoDao {
    @Query("SELECT * from TodoItem")
    suspend fun selectAll(): List<TodoItem>

    @Query("SELECT * from TodoItem WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): TodoItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TodoItem)

    @Update
    suspend fun update(item: TodoItem)

    @Query("DELETE FROM TodoItem")
    suspend fun deleteAll()
}
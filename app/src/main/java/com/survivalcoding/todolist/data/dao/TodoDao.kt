package com.survivalcoding.todolist.data.dao

import androidx.room.*
import com.survivalcoding.todolist.domain.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE id Like (:id)")
    suspend fun loadById(id: Int): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}

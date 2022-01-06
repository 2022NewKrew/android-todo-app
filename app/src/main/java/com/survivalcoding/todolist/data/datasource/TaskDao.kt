package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.survivalcoding.todolist.domain.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY isDone, date DESC")
    fun getAllLive(): LiveData<List<Task>>

    @Query("SELECT * FROM task ORDER BY isDone, date DESC")
    suspend fun getAllList(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg tasks: Task)

    @Delete
    suspend fun delete(task: Task)
}
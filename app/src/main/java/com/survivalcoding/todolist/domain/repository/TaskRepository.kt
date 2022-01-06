package com.survivalcoding.todolist.domain.repository

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.domain.entity.Task

interface TaskRepository {
    fun getTasksLive(): LiveData<List<Task>>
    suspend fun getTasksList(): List<Task>
    suspend fun upsert(task: Task)
    suspend fun delete(task: Task)
}
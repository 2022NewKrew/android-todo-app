package com.survivalcoding.todolist.domain.repository

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.domain.entity.Task

interface TaskRepository {
    fun getTasksLive(): LiveData<List<Task>>
    fun getTasksList(): List<Task>
    fun upsert(task: Task)
    fun delete(task: Task)
}
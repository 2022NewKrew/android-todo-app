package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.domain.entity.Task

interface TaskLocalDataSource {
    fun getTasksLive(): LiveData<List<Task>>
    fun deleteTask(newTask: Task)
    fun upsertTask(newTask: Task)
    fun getTasksList(): List<Task>
}
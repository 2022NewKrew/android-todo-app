package com.survivalcoding.todolist.data.datasource

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.domain.entity.Task

interface TaskLocalDataSource {
    fun getTasks(): LiveData<List<Task>>
    fun deleteTask(newTask: Task)
    fun upsertTask(newTask: Task)
}
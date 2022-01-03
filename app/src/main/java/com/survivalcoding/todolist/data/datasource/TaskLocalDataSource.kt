package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.entity.Task

interface TaskLocalDataSource {
    val tasks: List<Task>

    fun updateTask(id: Long)
    fun deleteTask(id: Long)
    fun insertTask(newTask: Task)
}
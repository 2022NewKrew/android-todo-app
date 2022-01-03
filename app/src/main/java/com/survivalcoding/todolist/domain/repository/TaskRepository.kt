package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.entity.Task

interface TaskRepository {
    val tasks: List<Task>

    fun update(id: Long)
    fun insert(task: Task)
    fun delete(id: Long)
}
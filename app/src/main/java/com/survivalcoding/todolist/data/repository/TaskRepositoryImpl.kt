package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasource.TaskLocalDataSource
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.repository.TaskRepository

class TaskRepositoryImpl constructor(private val taskLocalDataSource: TaskLocalDataSource) :
    TaskRepository {
    override val tasks: List<Task> = taskLocalDataSource.tasks

    override fun update(id: Long) {
        taskLocalDataSource.updateTask(id)
    }

    override fun insert(task: Task) {
        taskLocalDataSource.insertTask(task)
    }

    override fun delete(id: Long) {
        taskLocalDataSource.deleteTask(id)
    }

}
package com.survivalcoding.todolist.data.repository

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.data.datasource.TaskLocalDataSource
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskLocalDataSource: TaskLocalDataSource
) : TaskRepository {
    override fun getTasksLive(): LiveData<List<Task>> {
        return taskLocalDataSource.getTasksLive()
    }

    override fun getTasksList(): List<Task> {
        return taskLocalDataSource.getTasksList()
    }

    override fun upsert(task: Task) {
        taskLocalDataSource.upsertTask(task)
    }

    override fun delete(task: Task) {
        taskLocalDataSource.deleteTask(task)
    }

}
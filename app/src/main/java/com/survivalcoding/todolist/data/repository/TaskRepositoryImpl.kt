package com.survivalcoding.todolist.data.repository

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.data.datasource.TaskDao
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskLocalDataSource: TaskDao
) : TaskRepository {
    override fun getTasksLive(): LiveData<List<Task>> {
        return taskLocalDataSource.getAllLive()
    }

    override suspend fun getTasksList(): List<Task> {
        return taskLocalDataSource.getAllList()
    }

    override suspend fun upsert(task: Task) {
        taskLocalDataSource.insert(task)
    }

    override suspend fun delete(task: Task) {
        taskLocalDataSource.delete(task)
    }

}
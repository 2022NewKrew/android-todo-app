package com.survivalcoding.todolist.data.repository

import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.data.datasource.TaskLocalDataSource
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.repository.TaskRepository

class TaskRepositoryImpl constructor(private val taskLocalDataSource: TaskLocalDataSource) :
    TaskRepository {
    override fun getTasks(): LiveData<List<Task>> {
        return taskLocalDataSource.getTasks()
    }

    override fun upsert(task: Task) {
        taskLocalDataSource.upsertTask(task)
    }

    override fun delete(task: Task) {
        taskLocalDataSource.deleteTask(task)
    }

}
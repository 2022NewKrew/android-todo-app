package com.survivalcoding.todolist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.data.datasource.TaskInMemoryDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Task

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository =
        TaskRepositoryImpl(TaskInMemoryDataSource(getApplication<Application>().applicationContext))
    private val _tasks get() = taskRepository.getTasks()

    fun getTasks(): LiveData<List<Task>> = _tasks

    fun upsertTask(task: Task) {
        taskRepository.upsert(task)
    }

    fun deleteTask(task: Task) {
        taskRepository.delete(task)
    }
}
package com.survivalcoding.todolist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.survivalcoding.todolist.data.datasource.TaskInMemoryDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Task
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository =
        TaskRepositoryImpl(TaskInMemoryDataSource(application))
    private val _tasksLive get() = taskRepository.getTasksLive()
    private val _tasksList get() = taskRepository.getTasksList()

    private var mode = false
    private var task = Task(Date().time, "empty", "empty", Date().time, false)

    fun getMode() = mode
    fun setMode(isAddMode: Boolean) {
        mode = isAddMode
    }

    fun getTask() = task
    fun setTask(selectedTask: Task) {
        task = selectedTask
    }

    fun getTasksLive(): LiveData<List<Task>> = _tasksLive

    fun getTasksList(): List<Task> = _tasksList

    fun upsertTask(task: Task) {
        taskRepository.upsert(task)
    }

    fun deleteTask(task: Task) {
        taskRepository.delete(task)
    }
}
package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.datasource.TaskInMemoryDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Task

class MainViewModel() : ViewModel() {
    private val taskRepository = TaskRepositoryImpl(TaskInMemoryDataSource())
    private val _tasks = MutableLiveData(taskRepository.tasks)
    val tasks: LiveData<List<Task>> get() = _tasks

    fun updateTask(id: Long) {
        taskRepository.update(id)
        _tasks.value = taskRepository.tasks
    }

    fun deleteTask(id: Long) {
        taskRepository.delete(id)
        _tasks.value = taskRepository.tasks
    }

    fun insertTask(task: Task) {
        taskRepository.insert(task)
        _tasks.value = taskRepository.tasks
    }
}
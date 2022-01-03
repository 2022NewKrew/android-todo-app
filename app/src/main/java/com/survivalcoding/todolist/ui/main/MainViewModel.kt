package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.datasource.TaskMockDataSource
import com.survivalcoding.todolist.data.repository.TaskRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Task

// ViewModel 이 액티비티의 context 를 쓰게 되면, 액티비티가 destroy 된 경우에는 메모리 릭이 발생할 수 있다.
class MainViewModel() : ViewModel() {
    private val taskRepository = TaskRepositoryImpl(TaskMockDataSource())
    private val _tasks = MutableLiveData(taskRepository.tasks)
    val tasks: LiveData<List<Task>> = _tasks

    fun updateTask(id: Long) {
        taskRepository.update(id)
        _tasks.value = taskRepository.tasks
    }

    fun deleteTask(task: Task) {
        taskRepository.delete(task.id)
    }

    fun insertTask(task: Task) {
        taskRepository.insert(task)
        _tasks.value = taskRepository.tasks
    }
}
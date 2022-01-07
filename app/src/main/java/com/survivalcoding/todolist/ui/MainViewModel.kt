package com.survivalcoding.todolist.ui

import androidx.lifecycle.*
import com.survivalcoding.todolist.domain.entity.Task
import com.survivalcoding.todolist.domain.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val live = MutableLiveData<List<Task>>()
    private val _tasksLive get() = taskRepository.getTasksLive()
    private var _tasksList = listOf<Task>()

    private var mode = false
    private var task = Task(Date().time, "", "", Date().time, false)

    init {
        viewModelScope.launch {
            _tasksList = taskRepository.getTasksList()
            live.value = _tasksList
        }
    }

    fun getMode() = mode
    fun setMode(isAddMode: Boolean) {
        mode = isAddMode
    }

    fun getTask() = task
    fun setTask(selectedTask: Task) {
        task = selectedTask
    }

    fun getTasksLive(): LiveData<List<Task>> = _tasksLive
//    fun getTasksLive(): LiveData<List<Task>> {
//        live.value = getTasksList()
//        return live
//    }


    fun getTasksList(): List<Task> {
        viewModelScope.launch {
            _tasksList = taskRepository.getTasksList()
        }
        return _tasksList
    }

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.upsert(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T
        else throw IllegalArgumentException()
    }
}
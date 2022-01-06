package com.survivalcoding.todolist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.data.datasources.local.TodoRoomDataSource
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.models.TodoItem
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    //    private val repository = TodoRepositoryImpl(TodoMockDataSource())
    private val repository = TodoRepositoryImpl(TodoRoomDataSource(application.applicationContext))
    private val _data = MutableLiveData<List<TodoItem>>()
    val data: LiveData<List<TodoItem>> = _data

    init {
        viewModelScope.launch {
            setTodos(repository.getTodos())
        }
    }

    fun toggleTodoDone(id: Long) {
        viewModelScope.launch {
            val prevData = repository.select(id)
            val newData =
                prevData.copy(isDone = !prevData.isDone)
            repository.update(id, newData)
            _data.value = repository.getTodos()
        }
    }

    fun setTodos(todos: List<TodoItem>) {
        viewModelScope.launch {
            repository.setData(todos)
            _data.value = repository.getTodos()
        }
    }

    fun addTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.insert(todoItem)
            _data.value = repository.getTodos()
        }
    }
}
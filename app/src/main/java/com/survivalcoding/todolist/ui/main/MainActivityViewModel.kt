package com.survivalcoding.todolist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.survivalcoding.todolist.data.datasources.local.TodoRoomDataSource
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.models.TodoItem

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    //    private val repository = TodoRepositoryImpl(TodoMockDataSource())
    private val repository = TodoRepositoryImpl(TodoRoomDataSource(application.applicationContext))
    private val _data = MutableLiveData(repository.getTodos())
    val data: LiveData<List<TodoItem>> = _data

    fun toggleTodoDone(id: Long) {
        val prevData = repository.select(id)
        val newData =
            prevData.copy(isDone = !prevData.isDone)
        repository.update(id, newData)
        _data.value = repository.getTodos()
    }

    fun setTodos(todos: List<TodoItem>) {
        repository.setData(todos)
        _data.value = repository.getTodos()
    }

    fun addTodo(todoItem: TodoItem) {
        repository.insert(todoItem)
        _data.value = repository.getTodos()
    }
}
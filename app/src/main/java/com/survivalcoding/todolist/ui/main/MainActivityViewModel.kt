package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.datasources.local.TodoMockDataSource
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.models.TodoItem

class MainActivityViewModel : ViewModel() {
    private val repository = TodoRepositoryImpl(TodoMockDataSource())
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
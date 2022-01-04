package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.repository.TodoRepositoryImpl
import com.survivalcoding.todolist.model.Todo

class MainViewModel : ViewModel() {
    private val repository = TodoRepositoryImpl()
    private val _todoList = MutableLiveData(repository.todoList)
    val todoList get() = _todoList

    fun updateIsDone(todo: Todo) {
        repository.updateItem(todo.copy(id = todo.id, title = todo.title, isDone = !todo.isDone))
        _todoList.value = repository.todoList
    }

    fun addItem(todo: Todo) {
        repository.addItem(todo)
        _todoList.value = repository.todoList
    }

    fun updateItem(todo: Todo) {
        repository.updateItem(todo)
        _todoList.value = repository.todoList
    }

    fun deleteItem(todo: Todo) {
        repository.deleteItem(todo)
        _todoList.value = repository.todoList
    }
}
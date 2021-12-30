package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.model.Todo

class MainViewModel: ViewModel() {
    private val repository = TodoRepository()
    val todoList get() = repository.todoList

    fun updateList(todo: Todo) {
        repository.updateList(todo)
    }
}
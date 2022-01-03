package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository

class MainViewModel: ViewModel() {

    private val todoRepository = TodoRepository()

    val todos get() = todoRepository.todos

    fun toggleTodos(id: Long) {
        todoRepository.toggleTodo(id)
    }
}
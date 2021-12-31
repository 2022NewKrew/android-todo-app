package com.survivalcoding.todolist

import androidx.lifecycle.ViewModel

class MainActivityViewModel(): ViewModel() {

    private val todoRepository = TodoRepository()

    val todos get() = todoRepository.todos

    fun toggleTodos(id: Long) {
        todoRepository.toggleTodo(id)
    }
}
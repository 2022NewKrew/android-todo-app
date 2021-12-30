package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.model.Todo

class MainViewModel : ViewModel() {
    private val todoRepository = TodoRepository()

    val todos get() = todoRepository.todos

    fun toggleTodo(todo: Todo) {
        todoRepository.updateTodo(todo)
    }

    fun setTodos(todos: List<Todo>) {
        todoRepository.todos = todos
    }
}
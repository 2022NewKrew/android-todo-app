package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.model.Todo

class MainViewModel: ViewModel() {

    private val todoRepository = TodoRepository()

    private val _todos = MutableLiveData(todoRepository.todos)
    val todos: LiveData<List<Todo>> = _todos

    fun upsertTodo(todo: Todo) {
        todoRepository.upsertTodo(todo)
        _todos.value = todoRepository.todos
    }
}
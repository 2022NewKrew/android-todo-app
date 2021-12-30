package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.domain.entity.Todo

class MainViewModel : ViewModel() {

    private val todoRepository = TodoRepository()

    fun get(): List<Todo> = todoRepository.todos

    fun toggleIsDone(position: Int) {
        todoRepository.upDateIsDone(position)
    }

}
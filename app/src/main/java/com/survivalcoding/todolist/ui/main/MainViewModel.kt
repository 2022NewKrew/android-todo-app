package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepositoryImpl
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.usecase.GetTodosUseCase

class MainViewModel : ViewModel() {

    private val todoRepositoryImpl = TodoRepositoryImpl()
    private val getTodosUseCase = GetTodosUseCase(todoRepositoryImpl)
    private val _todos = MutableLiveData(getTodosUseCase())
    val todos: LiveData<List<Todo>> = _todos

    fun toggleIsDone(item: Todo) {
        todoRepositoryImpl.upDateIsDone(item)
        _todos.value = todoRepositoryImpl.getTodos()
    }

    fun addTodo(title: String) {
        todoRepositoryImpl.insert(title)
        _todos.value = todoRepositoryImpl.getTodos()
    }

}
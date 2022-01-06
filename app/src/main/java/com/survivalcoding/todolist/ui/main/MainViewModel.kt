package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.entity.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val todoRepositoryImpl: TodoRepository) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos = _todos
    private val _todoNeedChanged = MutableLiveData<Todo?>(null)
    val todoNeedChanged get() = _todoNeedChanged

    init {
        viewModelScope.launch {
            _todos.value = todoRepositoryImpl.getTodos()
        }
    }

    fun toggleIsDone(todo: Todo) {
        viewModelScope.launch {
            todoRepositoryImpl.update(todo)
            _todos.value = todoRepositoryImpl.getTodos()
        }
    }

    fun upsertTodo(title: String) {
        viewModelScope.launch {
            _todoNeedChanged.value?.let {
                todoRepositoryImpl.update(it.copy(title = title, timestamp = Date().time))
                _todoNeedChanged.value = null
            } ?: todoRepositoryImpl.insert(Todo(title = title))
            _todos.value = todoRepositoryImpl.getTodos()
        }

    }

    fun removeTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepositoryImpl.delete(todo)
            _todos.value = todoRepositoryImpl.getTodos()
        }
    }

    fun searchTodos(name: String?): List<Todo>? {
        if (name.isNullOrEmpty()) return todos.value

        return todos.value?.filter {
            name.lowercase() in it.title.lowercase()
        }
    }
}

class MainViewModelFactory(private val repository: TodoRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T
        else
            throw IllegalArgumentException()
    }
}
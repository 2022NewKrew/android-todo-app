package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    application: Application,
    private val todoRepository: TodoRepository,
) : AndroidViewModel(application) {
    var currentTodo: Todo? = null

    private val _todos = MutableStateFlow<List<Todo>>(listOf())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        viewModelScope.launch {

        }
    }

    fun toggleTodo(todo: Todo) {
        val newTodo = todo.copy(isDone = !todo.isDone)

        viewModelScope.launch {
            todoRepository.update(newTodo) // 오래 걸릴 수 있는 애
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            if (currentTodo != null) {
                todoRepository.update(
                    Todo(
                        id = currentTodo!!.id,
                        title = title,
                        timestamp = Date().time
                    )
                )
            } else {
                todoRepository.insert(
                    Todo(
                        title = title
                    )
                )
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }

    class MainViewModelFactory(
        private val application: Application,
        private val todoRepository: TodoRepository
    ) :
        ViewModelProvider.AndroidViewModelFactory(
            application
        ) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java))
                return MainViewModel(
                    application = application,
                    todoRepository = todoRepository
                ) as T
            else throw IllegalArgumentException()
        }
    }
}
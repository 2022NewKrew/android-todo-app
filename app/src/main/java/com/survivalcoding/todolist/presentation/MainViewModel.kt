package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.*
import com.survivalcoding.todolist.App
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import com.survivalcoding.todolist.domain.usecase.*
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    application: Application,
    private val todoUseCases: TodoUseCases
) : AndroidViewModel(application) {
    var currentTodo: Todo? = null

    val todos: LiveData<List<Todo>> = todoUseCases.getTodosUseCase().asLiveData()

    fun toggleTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCases.toggleTodoUseCase(todo)
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            todoUseCases.saveTodoUseCase(currentTodo, title)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCases.deleteTodoUseCase(todo)
        }
    }

    class MainViewModelFactory(
        private val application: Application,
    ) :
        ViewModelProvider.AndroidViewModelFactory(
            application
        ) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java))
                return MainViewModel(
                    application = application,
                    todoUseCases = (application as App).todoUseCases
                ) as T
            else throw IllegalArgumentException()
        }
    }
}
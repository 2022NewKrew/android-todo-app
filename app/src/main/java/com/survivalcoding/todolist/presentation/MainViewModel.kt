package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.survivalcoding.todolist.data.data_source.local.AppDatabase
import com.survivalcoding.todolist.data.repository.TodoRoomRepository
import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var currentTodo: Todo? = null

//    private val todoRepository = TodoInMemoryRepository()
    private val todoRepository = TodoRoomRepository(
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "todo-db"
        ).build().todoDao()
    )

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            _todos.value = todoRepository.getTodos()
        }
    }

    fun toggleTodo(todo: Todo) {
        val newTodo = todo.copy(isDone = !todo.isDone)

        viewModelScope.launch {
            todoRepository.update(newTodo) // 오래 걸릴 수 있는 애
            _todos.value = todoRepository.getTodos()
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
            _todos.value = todoRepository.getTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
            _todos.value = todoRepository.getTodos()
        }
    }
}
package com.survivalcoding.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.survivalcoding.todolist.data.data_source.local.AppDatabase
import com.survivalcoding.todolist.data.repository.TodoRoomRepository
import com.survivalcoding.todolist.domain.model.Todo
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var currentTodo: Todo? = null

//    private val todoRepository = TodoInMemoryRepository()
    private val todoRepository = TodoRoomRepository(
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "todo-db"
        ).allowMainThreadQueries().build().todoDao()
    )

    private val _todos = MutableLiveData(
        todoRepository.getTodos()
    )
    val todos: LiveData<List<Todo>> = _todos

    fun toggleTodo(todo: Todo) {
        val newTodo = todo.copy(isDone = !todo.isDone)
        todoRepository.update(newTodo)

        _todos.value = todoRepository.getTodos()
    }

    fun saveTodo(title: String) {
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

    fun deleteTodo(todo: Todo) {
        todoRepository.delete(todo)
        _todos.value = todoRepository.getTodos()
    }
}
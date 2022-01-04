package com.survivalcoding.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.domain.model.Todo
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class MainViewModel : ViewModel() {
    var currentTodo: Todo? = null

    private val currentId = AtomicInteger(0)

    private val todoRepository = TodoRepository()

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
                    id = currentId.getAndIncrement(),
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
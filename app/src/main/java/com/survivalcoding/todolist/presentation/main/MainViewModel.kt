package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.TodoRepository
import com.survivalcoding.todolist.domain.model.Todo
import java.util.concurrent.atomic.AtomicInteger

class MainViewModel : ViewModel() {
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

    fun addTodo(title: String) {
        todoRepository.insert(
            Todo(
                id = currentId.getAndIncrement(),
                title = title
            )
        )
        _todos.value = todoRepository.getTodos()
    }

    fun deleteTodo(todo: Todo) {
        todoRepository.delete(todo)
        _todos.value = todoRepository.getTodos()
    }
}
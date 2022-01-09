package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.data.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow

class TodoMemoryRepository : TodoRepository {
    private var nextId = 31

    private val _todos = MutableStateFlow(
        (1 until nextId).map {
            Todo(
                it,
                "Title #${it}",
                "Content #$it"
            )
        }
    )
    override val todos = _todos

    override suspend fun upsertTodo(todo: Todo) {
        todos.value = _todos.value.toMutableList().apply {
            val index = indexOfFirst { it.id == todo.id }
            if(index != -1) {
                this[index] = todo.copy()
            } else {
                add(todo.copy(id = nextId))
                nextId += 1
            }
        }.toList()
    }

    override suspend fun deleteTodo(todo: Todo) {
        _todos.value = _todos.value.toMutableList().apply {
            val index = indexOfFirst { it.id == todo.id }
            if(index != -1) {
                removeAt(index)
            }
        }.toList()
    }
}
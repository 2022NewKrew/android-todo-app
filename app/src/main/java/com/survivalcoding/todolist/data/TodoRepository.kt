package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo

class TodoRepository {

    private var _todos = (1..30).map {
        Todo(
            it.toLong(),
            "Title #${it}",
            "Content #$it",
            0L,
            false
        )
    }

    val todos get() = _todos.map { it.copy() }

    fun toggleTodo(id: Long) {
        todos.find { it.id == id }?.run {
            updateTodo(copy(hasHighlight = !hasHighlight))
        }
    }

    fun updateTodo(todo: Todo) {
        _todos = _todos.map {
            if(it.id == todo.id) todo else it
        }
    }
}
package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo

class TodoRepository {

    private var nextId = 31L

    private var _todos = (1 until nextId).map {
        Todo(
            it.toLong(),
            "Title #${it}",
            "Content #$it",
            0L,
            false
        )
    }

    val todos get() = _todos.map { it.copy() }

    fun upsertTodo(todo: Todo) {
        if (todo.id < 0) {
            insertTodo(todo)
        } else {
            updateTodo(todo)
        }
    }

    fun toggleTodo(id: Long) {
        todos.find { it.id == id }?.run {
            updateTodo(copy(isDone = !isDone))
        }
    }

    fun insertTodo(newTodo: Todo) {
        _todos = _todos + listOf(newTodo.copy(id = nextId))
        nextId += 1
    }

    fun updateTodo(todo: Todo) {
        _todos = _todos.map {
            if(it.id == todo.id) todo else it
        }
    }
}
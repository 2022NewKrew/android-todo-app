package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo

class TodoRepository {

    private var nextId = 31L

    private val _todos = (1 until nextId).map {
        Todo(
            it.toLong(),
            "Title #${it}",
            "Content #$it",
            false,
            System.currentTimeMillis()
        )
    }.toMutableList()

    val todos get() = _todos.toList()

    fun upsertTodo(todo: Todo) {
        if (todo.id < 0) {
            insertTodo(todo)
        } else {
            updateTodo(todo)
        }
    }

    fun insertTodo(newTodo: Todo) {
        _todos.add(newTodo.copy(id = nextId))
        nextId += 1
    }

    fun updateTodo(todo: Todo) {
        _todos[_todos.indexOfFirst { it.id == todo.id }] = todo.copy()
    }

    fun deleteTodo(todo: Todo) {
        _todos.removeAt(_todos.indexOfFirst { it.id == todo.id })
    }
}
package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.model.Todo

class TodoRepository {
    // 변경 안되는 리스트
    private var todos = mutableListOf<Todo>()

    fun getTodos(): List<Todo> = todos

    fun insert(todo: Todo) {
        todos = todos.toMutableList()
            .apply { add(todo) }
    }

    fun delete(todo: Todo) {
        todos = todos.filter { it.id != todo.id }.toMutableList()
    }

    fun update(todo: Todo) {
        todos = todos.map {
            if (it.id == todo.id) {
                todo
            } else {
                it
            }
        }.toMutableList()
    }
}
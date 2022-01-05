package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo
import java.util.concurrent.atomic.AtomicInteger

class TodoInMemoryRepository {
    private val currentId = AtomicInteger(0)

    // 변경 안되는 리스트
    private var todos = mutableListOf<Todo>()

    fun getTodos(): List<Todo> = todos

    fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    fun insert(todo: Todo) {
        todos = todos.plus(
            todo.copy(
                id = currentId.getAndIncrement(),
            )
        ).toMutableList()
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
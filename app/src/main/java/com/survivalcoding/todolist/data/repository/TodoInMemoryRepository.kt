package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import java.util.concurrent.atomic.AtomicInteger

class TodoInMemoryRepository: TodoRepository {
    private val currentId = AtomicInteger(0)

    // 변경 안되는 리스트
    private var todos = mutableListOf<Todo>()

    override suspend fun getTodos(): List<Todo> = todos

    override suspend fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    override suspend fun insert(todo: Todo) {
        todos = todos.plus(
            todo.copy(
                id = currentId.getAndIncrement(),
            )
        ).toMutableList()
    }

    override suspend fun delete(todo: Todo) {
        todos = todos.filter { it.id != todo.id }.toMutableList()
    }

    override suspend fun update(todo: Todo) {
        todos = todos.map {
            if (it.id == todo.id) {
                todo
            } else {
                it
            }
        }.toMutableList()
    }
}
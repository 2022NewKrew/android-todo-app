package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo

class TodosInMemoryRepository : TodosLocalRepository {
    private var todos = listOf<Todo>()

    override suspend fun getTodos(): List<Todo> =
        todos.sortedWith(compareBy({ it.isDone }, { it.dueDate }, { -it.createDate }))

    override suspend fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    override suspend fun addTodo(todo: Todo) {
        todos = todos.plus(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        todos = todos.map { origin ->
            if (origin.id == todo.id)
                todo
            else origin
        }
    }

    override suspend fun deleteTodo(todo: Todo) {
        todos = todos.minus(todo)
    }
}
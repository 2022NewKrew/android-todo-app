package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.Todo

class TodosInMemoryRepository : TodosLocalRepository {
    private var todos = listOf<Todo>()

    override fun getTodos(): List<Todo> =
        todos.sortedWith(compareBy({ it.isDone }, { it.dueDate }, { -it.createDate }))

    override fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    override fun addTodo(todo: Todo) {
        todos = todos.plus(todo)
    }

    override fun updateTodo(todo: Todo) {
        todos = todos.map { origin ->
            if (origin.id == todo.id)
                todo
            else origin
        }
    }

    override fun deleteTodo(todo: Todo) {
        todos = todos.minus(todo)
    }
}
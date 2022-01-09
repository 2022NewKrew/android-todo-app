package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.Todo

class TodoInMemoryDataSource() : TodoDataSource {

    private var todoList: MutableList<Todo> = mutableListOf()

    override suspend fun getTodoList(): List<Todo> =
        todoList

    override suspend fun insertItem(todo: Todo) {
        todoList.add(todo.copy(id = todoList.size + 1))
    }

    override suspend fun updateItem(todo: Todo) {
        todoList = todoList.map {
            if (it.id == todo.id) todo
            else it
        }.toMutableList()
    }

    override suspend fun deleteItem(todo: Todo) {
        todoList.remove(todo)
    }

    override suspend fun search(query: String): List<Todo> =
        todoList.filter { it.title.contains(query, ignoreCase = true) }
}
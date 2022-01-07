package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TodoInMemoryDataSource(
    private val ioDispatcher: CoroutineDispatcher
) : TodoDataSource {

    private var todoList: MutableList<Todo> = mutableListOf()

    override suspend fun getTodoList(): List<Todo> = withContext(ioDispatcher) {
        todoList
    }

    override suspend fun insertItem(todo: Todo) {
        withContext(ioDispatcher) {
            todoList.add(todo.copy(id = todoList.size + 1))
        }
    }

    override suspend fun updateItem(todo: Todo) = withContext(ioDispatcher) {
        todoList = todoList.map {
            if (it.id == todo.id) todo
            else it
        }.toMutableList()
    }

    override suspend fun deleteItem(todo: Todo) {
        withContext(ioDispatcher) {
            todoList.remove(todo)
        }
    }

    override suspend fun search(query: String): List<Todo> = withContext(ioDispatcher) {
        todoList.filter { it.title.contains(query, ignoreCase = true) }
    }
}
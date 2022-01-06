package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasource.TodoLocalDataSource
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(private val todoDataSource: TodoLocalDataSource) : TodoRepository {
    override suspend fun getTodoList(): List<Todo> = todoDataSource.getTodoList()

    override suspend fun insertItem(todo: Todo) = todoDataSource.insertItem(todo)

    override suspend fun updateItem(todo: Todo) = todoDataSource.updateItem(todo)

    override suspend fun deleteItem(todo: Todo) = todoDataSource.deleteItem(todo)

    override suspend fun search(query: String): List<Todo> = todoDataSource.search(query)
}
package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.Todo

interface TodoDataSource {
    suspend fun getTodoList(): List<Todo>
    suspend fun insertItem(todo: Todo)
    suspend fun updateItem(todo: Todo)
    suspend fun deleteItem(todo: Todo)
    suspend fun search(query: String): List<Todo>
}
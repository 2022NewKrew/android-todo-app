package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodoRepository {
    suspend fun getTodoList(): List<Todo>
    suspend fun insertItem(todo: Todo)
    suspend fun updateItem(todo: Todo)
    suspend fun deleteItem(todo: Todo)
    suspend fun search(query: String): List<Todo>
}
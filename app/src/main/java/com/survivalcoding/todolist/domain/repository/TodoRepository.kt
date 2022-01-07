package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodoRepository {
    suspend fun getTodos(): List<Todo>

    suspend fun getTodoById(id: Int): Todo?

    suspend fun insert(todo: Todo)

    suspend fun delete(todo: Todo)

    suspend fun update(todo: Todo)
}
package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.entity.Todo


interface TodoRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun update(todo: Todo)
    suspend fun insert(todo: Todo)
    suspend fun delete(todo: Todo)
}
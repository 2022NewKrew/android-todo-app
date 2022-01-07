package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodosLocalRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun getTodoById(id: Int): Todo?
    suspend fun addTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}
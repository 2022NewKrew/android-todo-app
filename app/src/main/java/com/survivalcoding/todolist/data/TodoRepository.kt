package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.data.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    val todos: Flow<List<Todo>>

    suspend fun upsertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}
package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.entity.Todo
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    fun update(todo: Todo)
    fun insert(todo: Todo)
    fun delete(todo: Todo)
}
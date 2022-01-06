package com.survivalcoding.todolist.domain.interfaces

import com.survivalcoding.todolist.domain.models.TodoItem

interface TodoRepository : Repository<TodoItem, Long> {
    override suspend fun insert(item: TodoItem): Boolean
    override suspend fun select(id: Long): TodoItem?
    override suspend fun update(id: Long, item: TodoItem): Boolean
    override suspend fun delete(id: Long): Boolean
    suspend fun clear()
}
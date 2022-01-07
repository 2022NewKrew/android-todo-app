package com.survivalcoding.todolist.domain.interfaces

import com.survivalcoding.todolist.domain.models.TodoItem

interface TodoDataSource {
    suspend fun getData(): List<TodoItem>
    suspend fun setData(list: List<TodoItem>)
    suspend fun getById(id: Long): TodoItem
    suspend fun insert(todoItem: TodoItem)
    suspend fun update(todoItem: TodoItem)
    suspend fun deleteAll()
}
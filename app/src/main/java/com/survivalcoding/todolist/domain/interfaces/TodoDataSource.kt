package com.survivalcoding.todolist.domain.interfaces

import com.survivalcoding.todolist.domain.models.TodoItem

interface TodoDataSource {
    fun getData(): List<TodoItem>
    fun getById(id: Long): TodoItem
    fun insert(todoItem: TodoItem)
    fun update(todoItem: TodoItem)
}
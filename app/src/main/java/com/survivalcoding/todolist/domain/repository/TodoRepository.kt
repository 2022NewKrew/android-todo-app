package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodoRepository {
    fun addItem(todo: Todo)
    fun updateItem(todo: Todo)
    fun deleteItem(todo: Todo)
}
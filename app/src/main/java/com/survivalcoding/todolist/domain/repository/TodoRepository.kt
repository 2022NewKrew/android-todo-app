package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.model.Todo

interface TodoRepository {
    fun updateList(todo: Todo)
    fun addItem(todo: Todo)
    fun updateItem(todo: Todo)
    fun deleteItem(todo: Todo)
}
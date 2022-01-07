package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.ToDo

interface ToDoRepository {
    suspend fun updateItem(id: Long, newItem: ToDo)
    suspend fun deleteItem(id: Long)
    suspend fun addItem(newItem: ToDo)
    suspend fun getAllItem(): List<ToDo>
    suspend fun getMatchingItem(query: String): List<ToDo>
}
package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo

interface ToDoLocalDataSource {
    suspend fun updateItem(id: Long, newItem: ToDo)
    suspend fun deleteItem(id: Long)
    suspend fun addItem(newItem: ToDo)
    suspend fun getAllItem(): List<ToDo>
    suspend fun getMatchingItems(query: String): List<ToDo>
}
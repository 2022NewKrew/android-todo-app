package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoLocalDataSource {
    fun updateItem(id: Long, newItem: ToDo)
    fun deleteItem(id: Long)
    fun addItem(newItem: ToDo)
    fun getMatchingItems(query: String): Flow<List<ToDo>>
}
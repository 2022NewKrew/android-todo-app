package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun updateItem(id: Long, newItem: ToDo)
    fun deleteItem(id: Long)
    fun addItem(newItem: ToDo)
    fun getMatchingItem(query: String): Flow<List<ToDo>>
}
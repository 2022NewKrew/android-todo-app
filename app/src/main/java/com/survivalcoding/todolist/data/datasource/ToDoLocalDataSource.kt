package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.ToDo
import kotlinx.coroutines.flow.StateFlow

interface ToDoLocalDataSource {
    val toDoList: StateFlow<List<ToDo>>

    fun updateItem(id: Long, newItem: ToDo)
    fun deleteItem(id: Long)
    fun addItem(newItem: ToDo)
}
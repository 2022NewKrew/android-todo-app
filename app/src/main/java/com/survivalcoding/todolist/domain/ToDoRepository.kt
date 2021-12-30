package com.survivalcoding.todolist.domain

import com.survivalcoding.todolist.ToDo
import kotlinx.coroutines.flow.StateFlow

interface ToDoRepository {
    val toDoList: StateFlow<List<ToDo>>

    fun updateItem(id: Long, newItem: ToDo)
    fun deleteItem(id: Long)
}
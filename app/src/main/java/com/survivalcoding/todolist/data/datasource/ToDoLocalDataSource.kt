package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.ToDo
import kotlinx.coroutines.flow.StateFlow

interface ToDoLocalDataSource {
    val toDoList: StateFlow<List<ToDo>>

    fun updateItem(position: Int, newItem: ToDo)
}
package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.ToDo
import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.domain.ToDoRepository

class ToDoRepositoryImpl constructor(private val toDoLocalDataSource: ToDoLocalDataSource) :
    ToDoRepository {
    override val toDoList = toDoLocalDataSource.toDoList

    override fun updateItem(position: Int, newItem: ToDo) =
        toDoLocalDataSource.updateItem(position, newItem)
}
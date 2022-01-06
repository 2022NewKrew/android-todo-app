package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoLocalDataSource: ToDoLocalDataSource) :
    ToDoRepository {
    override val toDoList = toDoLocalDataSource.toDoList

    override fun updateItem(id: Long, newItem: ToDo) =
        toDoLocalDataSource.updateItem(id, newItem)

    override fun deleteItem(id: Long) = toDoLocalDataSource.deleteItem(id)

    override fun addItem(newItem: ToDo) = toDoLocalDataSource.addItem(newItem)

    override fun searchItem(query: String) = toDoLocalDataSource.searchItem(query)
}
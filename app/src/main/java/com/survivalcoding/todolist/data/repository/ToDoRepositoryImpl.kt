package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoLocalDataSource: ToDoLocalDataSource) :
    ToDoRepository {

    override suspend fun updateItem(id: Long, newItem: ToDo) = toDoLocalDataSource.updateItem(id, newItem)

    override suspend fun deleteItem(id: Long) = toDoLocalDataSource.deleteItem(id)

    override suspend fun addItem(newItem: ToDo) = toDoLocalDataSource.addItem(newItem)

    override suspend fun getAllItem() = toDoLocalDataSource.getAllItem()

    override suspend fun getMatchingItem(query: String) = toDoLocalDataSource.getMatchingItems(query)
}
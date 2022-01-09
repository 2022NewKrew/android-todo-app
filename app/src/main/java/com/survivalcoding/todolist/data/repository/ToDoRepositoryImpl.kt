package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasource.ToDoLocalDataSource
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoLocalDataSource: ToDoLocalDataSource) :
    ToDoRepository {

    override suspend fun updateToDo(id: Long, newItem: ToDo) = toDoLocalDataSource.updateToDo(id, newItem)

    override suspend fun deleteToDo(id: Long) = toDoLocalDataSource.deleteToDo(id)

    override suspend fun addToDo(newItem: ToDo) = toDoLocalDataSource.addToDo(newItem)

    override suspend fun getAllToDo() = toDoLocalDataSource.getAllToDo()

    override suspend fun getMatchingToDos(query: String) = toDoLocalDataSource.getMatchingToDos(query)

    override suspend fun getToDosOrderByTimeAsc(query: String) = toDoLocalDataSource.getToDosOrderByTimeAsc(query)

    override suspend fun getToDosOrderByTimeDesc(query: String) = toDoLocalDataSource.getToDosOrderByTimeDesc(query)

    override suspend fun getToDosOrderByTitleAsc(query: String) = toDoLocalDataSource.getToDosOrderByTitleAsc(query)

    override suspend fun getToDosOrderByTitleDesc(query: String) = toDoLocalDataSource.getToDosOrderByTitleDesc(query)
}
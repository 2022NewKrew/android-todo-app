package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor() : ToDoLocalDataSource {

    private var toDoList: List<ToDo> = listOf()

    override suspend fun updateItem(id: Long, newItem: ToDo) {
        toDoList = toDoList.map {
            if (it.id == id) {
                newItem
            } else {
                it
            }
        }
    }

    override suspend fun deleteItem(id: Long) {
        toDoList = toDoList.filter {
            it.id != id
        }
    }

    override suspend fun addItem(newItem: ToDo) {
        toDoList = toDoList.plus(newItem)
    }

    override suspend fun getAllItem() = toDoList

    override suspend fun getMatchingItems(query: String) =
        toDoList.filter {
            it.title
                .lowercase()
                .contains(query.lowercase())
        }
}
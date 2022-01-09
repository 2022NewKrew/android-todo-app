package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor() : ToDoLocalDataSource {

    private var nextId = AtomicLong(1)

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
        toDoList = toDoList.plus(newItem.copy(id = nextId.getAndIncrement()))
    }

    override suspend fun getAllItem() = toDoList

    override suspend fun getMatchingItems(query: String) =
        toDoList.filter {
            it.title
                .lowercase()
                .contains(query.lowercase())
        }
}
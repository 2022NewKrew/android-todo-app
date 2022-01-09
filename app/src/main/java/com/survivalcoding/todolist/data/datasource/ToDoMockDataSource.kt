package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

class ToDoMockDataSource @Inject constructor() : ToDoLocalDataSource {

    private var nextId = AtomicLong(1)

    private var toDoList = (0L..30L).map {
        ToDo(
            id = it,
            title = "To Do List 만들기 $it"
        )
    }

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
        toDoList = toDoList.plus(newItem.copy(nextId.getAndIncrement()))
    }

    override suspend fun getAllItem() = toDoList

    override suspend fun getMatchingItems(query: String) =
        toDoList.filter {
            it.title
                .lowercase()
                .contains(query.lowercase())
        }
}
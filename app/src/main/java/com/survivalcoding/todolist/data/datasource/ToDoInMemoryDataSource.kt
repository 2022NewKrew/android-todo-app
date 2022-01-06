package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor() : ToDoLocalDataSource {

    private var inMemoryToDoList = listOf<ToDo>()

    private val _toDoList = MutableStateFlow<List<ToDo>>(listOf())
    override val toDoList = _toDoList.asStateFlow()

    override fun updateItem(id: Long, newItem: ToDo) {
        inMemoryToDoList = _toDoList.value.map { toDo ->
            if (toDo.id == id) {
                newItem
            } else {
                toDo
            }
        }
        _toDoList.value = inMemoryToDoList
    }

    override fun deleteItem(id: Long) {
        inMemoryToDoList = _toDoList.value.filter { toDo ->
            toDo.id != id
        }
        _toDoList.value = inMemoryToDoList
    }

    override fun addItem(newItem: ToDo) {
        inMemoryToDoList = _toDoList.value.plus(newItem)
        _toDoList.value = inMemoryToDoList
    }

    override fun searchItem(query: String) {
        _toDoList.value = inMemoryToDoList.filter {
            it.title.lowercase().contains(query.lowercase())
        }
    }
}
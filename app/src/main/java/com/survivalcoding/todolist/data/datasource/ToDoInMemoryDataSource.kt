package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor() : ToDoLocalDataSource {
    private val _toDoList = MutableStateFlow<List<ToDo>>(listOf())
    override val toDoList = _toDoList.asStateFlow()

    override fun updateItem(id: Long, newItem: ToDo) {
        _toDoList.value = _toDoList.value.map { toDo ->
            if (toDo.id == id) {
                newItem
            } else {
                toDo
            }
        }
    }

    override fun deleteItem(id: Long) {
        _toDoList.value = _toDoList.value.filter { toDo ->
            toDo.id != id
        }
    }

    override fun addItem(newItem: ToDo) {
        _toDoList.value = _toDoList.value.plus(newItem)
    }
}
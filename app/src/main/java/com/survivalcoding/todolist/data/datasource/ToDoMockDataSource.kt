package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ToDoMockDataSource @Inject constructor() : ToDoLocalDataSource {

    private var mockToDoList = (0L..30L).map {
        ToDo(
            id = it,
            title = "To Do List 만들기 $it"
        )
    }

    private val _toDoList = MutableStateFlow(mockToDoList)
    override val toDoList = _toDoList.asStateFlow()

    override fun updateItem(id: Long, newItem: ToDo) {
        mockToDoList = _toDoList.value.map { toDo ->
            if (toDo.id == id) {
                newItem
            } else {
                toDo
            }
        }
        _toDoList.value = mockToDoList
    }

    override fun deleteItem(id: Long) {
        mockToDoList = _toDoList.value.filter { toDo ->
            toDo.id != id
        }
        _toDoList.value = mockToDoList
    }

    override fun addItem(newItem: ToDo) {
        mockToDoList = _toDoList.value.plus(newItem)
        _toDoList.value = mockToDoList
    }

    override fun searchItem(query: String) {
        _toDoList.value = mockToDoList.filter {
            it.title.lowercase().contains(query.lowercase())
        }
    }
}
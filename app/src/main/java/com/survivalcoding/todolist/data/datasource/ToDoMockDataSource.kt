package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToDoMockDataSource : ToDoLocalDataSource {
    private val _toDoList = MutableStateFlow(
        (0L..30L).map {
            ToDo(
                id = it,
                title = "To Do List 만들기 $it"
            )
        }
    )
    override val toDoList = _toDoList.asStateFlow()

    override fun updateItem(position: Int, newItem: ToDo) {
        _toDoList.value = _toDoList.value.mapIndexed { index, toDo ->
            if (index == position) {
                newItem
            } else {
                toDo
            }
        }
    }
}
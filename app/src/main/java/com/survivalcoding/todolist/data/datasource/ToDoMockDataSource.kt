package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoMockDataSource @Inject constructor() : ToDoLocalDataSource {

    private var mockToDoList = (0L..30L).map {
        ToDo(
            id = it,
            title = "To Do List 만들기 $it"
        )
    }

    private val _toDoList = MutableStateFlow(mockToDoList)

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

    override fun getMatchingItems(query: String): Flow<List<ToDo>> {
        return _toDoList.flatMapLatest {
            flow {
                emit(it)
            }
        }
    }
}
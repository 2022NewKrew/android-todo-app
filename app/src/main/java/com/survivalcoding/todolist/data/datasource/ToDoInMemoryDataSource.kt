package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoInMemoryDataSource @Inject constructor() : ToDoLocalDataSource {

    private var inMemoryToDoList = listOf<ToDo>()

    private val _toDoList = MutableStateFlow<List<ToDo>>(listOf())

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

    override fun getMatchingItems(query: String): Flow<List<ToDo>> {
        return _toDoList.flatMapLatest {
            flow {
                emit(it)
            }
        }
    }
}
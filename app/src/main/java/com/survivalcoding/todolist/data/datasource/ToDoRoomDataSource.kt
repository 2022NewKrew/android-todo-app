package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.ToDoDatabase
import com.survivalcoding.todolist.data.dto.ToDoRoomDto
import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToDoRoomDataSource @Inject constructor(toDoDatabase: ToDoDatabase) : ToDoLocalDataSource {

    private val toDoDao = toDoDatabase.toDoDao()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            toDoDao.getAll().collectLatest {
                val toDoList = it.map { toDoRoomDto ->
                    convert(toDoRoomDto)
                }
                _toDoList.emit(toDoList)
            }
        }
    }

    private val _toDoList = MutableStateFlow<List<ToDo>>(listOf())
    override val toDoList: StateFlow<List<ToDo>> = _toDoList.asStateFlow()


    override fun updateItem(id: Long, newItem: ToDo) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.update(convert(newItem)) }
    }

    override fun deleteItem(id: Long) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.deleteById(id) }
    }

    override fun addItem(newItem: ToDo) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.insert(convert(newItem)) }
    }

    override fun searchItem(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            toDoDao.search(query).collectLatest {
                _toDoList.value = it.map { toDoRoomDto ->
                    convert(toDoRoomDto)
                }
            }
        }
    }

    private fun convert(toDoRoomDto: ToDoRoomDto) = ToDo(
        id = toDoRoomDto.id,
        title = toDoRoomDto.title,
        timeStamp = toDoRoomDto.timeStamp,
        isDone = toDoRoomDto.isDone
    )

    private fun convert(toDo: ToDo) = ToDoRoomDto(
        id = toDo.id,
        title = toDo.title,
        timeStamp = toDo.timeStamp,
        isDone = toDo.isDone
    )
}
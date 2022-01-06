package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.dao.ToDoDao
import com.survivalcoding.todolist.data.dto.ToDoRoomDto
import com.survivalcoding.todolist.domain.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToDoRoomDataSource @Inject constructor(private val toDoDao: ToDoDao) : ToDoLocalDataSource {

    override fun updateItem(id: Long, newItem: ToDo) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.update(convert(newItem)) }
    }

    override fun deleteItem(id: Long) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.deleteById(id) }
    }

    override fun addItem(newItem: ToDo) {
        GlobalScope.launch(Dispatchers.IO) { toDoDao.insert(convert(newItem)) }
    }

    override fun getMatchingItems(query: String): Flow<List<ToDo>> {
        return toDoDao.search(query).flatMapLatest {
            flow {
                emit(
                    it.map { toDoRoomDto ->
                        convert(toDoRoomDto)
                    }
                )
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
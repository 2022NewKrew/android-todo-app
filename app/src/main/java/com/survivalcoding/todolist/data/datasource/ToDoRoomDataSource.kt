package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.dao.ToDoDao
import com.survivalcoding.todolist.data.dto.ToDoRoomDto
import com.survivalcoding.todolist.domain.model.ToDo
import javax.inject.Inject

class ToDoRoomDataSource @Inject constructor(private val toDoDao: ToDoDao) : ToDoLocalDataSource {

    override suspend fun updateItem(id: Long, newItem: ToDo) = toDoDao.update(convert(newItem))

    override suspend fun deleteItem(id: Long) = toDoDao.deleteById(id)

    override suspend fun addItem(newItem: ToDo) = toDoDao.insert(convert(newItem))

    override suspend fun getAllItem() = toDoDao.getAll().map { convert(it) }

    override suspend fun getMatchingItems(query: String) = toDoDao.search(query).map { convert(it) }

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
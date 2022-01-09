package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.dao.ToDoDao
import com.survivalcoding.todolist.data.dto.ToDoRoomDto
import com.survivalcoding.todolist.domain.model.ToDo
import javax.inject.Inject

class ToDoRoomDataSource @Inject constructor(private val toDoDao: ToDoDao) : ToDoLocalDataSource {

    override suspend fun updateToDo(id: Long, newItem: ToDo) = toDoDao.update(convert(newItem))

    override suspend fun deleteToDo(id: Long) = toDoDao.deleteById(id)

    override suspend fun addToDo(newItem: ToDo) = toDoDao.insert(convert(newItem))

    override suspend fun getAllToDo() = toDoDao.getAll().map { convert(it) }

    override suspend fun getMatchingToDos(query: String) = toDoDao.search(query).map { convert(it) }

    override suspend fun getToDosOrderByTimeAsc(query: String) = toDoDao.getOrderByTimeAsc(query).map { convert(it) }

    override suspend fun getToDosOrderByTimeDesc(query: String) = toDoDao.getOrderByTimeDesc(query).map { convert(it) }

    override suspend fun getToDosOrderByTitleAsc(query: String) = toDoDao.getOrderByTitleAsc(query).map { convert(it) }

    override suspend fun getToDosOrderByTitleDesc(query: String) = toDoDao.getOrderByTitleDesc(query).map { convert(it) }

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
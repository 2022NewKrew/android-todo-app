package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasources.local.TodoRoomDataSource
import com.survivalcoding.todolist.domain.interfaces.TodoDataSource
import com.survivalcoding.todolist.domain.interfaces.TodoRepository
import com.survivalcoding.todolist.domain.models.TodoItem

class TodoRepositoryImpl(private val dataSource: TodoDataSource) : TodoRepository {
    suspend fun getTodos(): List<TodoItem> = dataSource.getData()

    suspend fun setData(targetData: List<TodoItem>) {
        dataSource.deleteAll()
        dataSource.setData(targetData.toMutableList())
    }

    override suspend fun insert(item: TodoItem): Boolean {
        dataSource.setData(dataSource.getData().plus(item))

        if (dataSource is TodoRoomDataSource) {
            dataSource.insert(item)
        }
        return true
    }

    override suspend fun select(id: Long): TodoItem {
        return dataSource.getData().first { it.id == id }
    }

    override suspend fun update(id: Long, item: TodoItem): Boolean {
        dataSource.update(item)
        return true
    }

    override suspend fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
        return true
    }

    override suspend fun clear() {
        dataSource.deleteAll()
    }
}
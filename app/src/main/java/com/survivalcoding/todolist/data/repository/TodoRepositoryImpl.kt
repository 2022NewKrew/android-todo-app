package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.datasources.local.TodoRoomDataSource
import com.survivalcoding.todolist.domain.interfaces.TodoDataSource
import com.survivalcoding.todolist.domain.interfaces.TodoRepository
import com.survivalcoding.todolist.domain.models.TodoItem

class TodoRepositoryImpl(private val dataSource: TodoDataSource) : TodoRepository {
    private var data = dataSource.getData().toMutableList()

    fun getTodos(): List<TodoItem> = data

    fun setData(targetData: List<TodoItem>) {
        data = targetData.toMutableList()
    }

    override fun insert(item: TodoItem): Boolean {
        data = data.plus(item).toMutableList()
        if (dataSource is TodoRoomDataSource) {
            dataSource.insert(item)
        }
        return true
    }

    override fun select(id: Long): TodoItem {
        return data.first { it.id == id }
    }

    override fun update(id: Long, item: TodoItem): Boolean {
        val idx = data.indexOfFirst { it.id == id }
        data[idx] = item
        if (dataSource is TodoRoomDataSource) {
            dataSource.update(item)
        }
        data = data.toMutableList()
        return true
    }

    override fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
        return true
    }

    override fun clear() {
        data = mutableListOf()
    }
}
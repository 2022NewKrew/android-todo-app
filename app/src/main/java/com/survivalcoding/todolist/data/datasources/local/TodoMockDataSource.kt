package com.survivalcoding.todolist.data.datasources.local

import com.survivalcoding.todolist.domain.interfaces.TodoDataSource
import com.survivalcoding.todolist.domain.models.TodoItem

class TodoMockDataSource : TodoDataSource {
    private val data = (1..30.toLong()).map {
        TodoItem(
            id = it,
            title = "title ${it.toInt()}",
            description = "description of task # ${it.toInt()}"
        )
    }.toMutableList()

    override fun getData(): List<TodoItem> = data
    override fun getById(id: Long) = data.first { it.id == id }

    override fun insert(todoItem: TodoItem) {
        data.add(todoItem)
    }

    override fun update(todoItem: TodoItem) {
        val idx = data.indexOfFirst { it.id == todoItem.id }
        data[idx] = todoItem
    }
}
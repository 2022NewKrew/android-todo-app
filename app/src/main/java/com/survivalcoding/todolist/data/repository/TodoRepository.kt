package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.model.TodoItem

object TodoRepository {
    private var data = (1..30.toLong()).map {
        TodoItem(
            id = it,
            title = "title ${it.toInt()}",
            description = "description of task # ${it.toInt()}"
        )
    }.toMutableList()

    fun getTodos(): List<TodoItem> = data

    fun removeAllData() {
        data = mutableListOf()
    }

    fun addAllData(targetData: List<TodoItem>) {
        data = targetData.toMutableList()
    }

    fun findDataById(id: Long): TodoItem {
        return data.first { it.id == id }
    }

    fun updateDataByIndex(index: Int, newItem: TodoItem) {
        data[index] = newItem
    }

    fun addData(todoItem: TodoItem) {
        data = data.plus(todoItem).toMutableList()
    }
}
package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.local.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

class TodoLocalDataSource(private val todoDao: TodoDao) : TodoDataSource {

    override suspend fun getTodoList(): List<Todo> = todoDao.getTodoList()

    override suspend fun insertItem(todo: Todo) {
        todoDao.insertItem(todo)
    }

    override suspend fun updateItem(todo: Todo) {
        todoDao.updateItem(todo)
    }

    override suspend fun deleteItem(todo: Todo) {
        todoDao.deleteItem(todo)
    }

    override suspend fun search(query: String): List<Todo> = todoDao.search(query)
}
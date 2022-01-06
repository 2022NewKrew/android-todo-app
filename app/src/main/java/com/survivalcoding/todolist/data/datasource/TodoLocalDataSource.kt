package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.local.TodoDao
import com.survivalcoding.todolist.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TodoLocalDataSource(
    private val todoDao: TodoDao,
    private val ioDispatcher: CoroutineDispatcher
) : TodoDataSource {

    override suspend fun getTodoList(): List<Todo> = withContext(ioDispatcher) {
        todoDao.getTodoList()
    }

    override suspend fun insertItem(todo: Todo) = withContext(ioDispatcher) {
        todoDao.insertItem(todo)
    }

    override suspend fun updateItem(todo: Todo) = withContext(ioDispatcher) {
        todoDao.updateItem(todo)
    }

    override suspend fun deleteItem(todo: Todo) = withContext(ioDispatcher) {
        todoDao.deleteItem(todo)
    }

    override suspend fun search(query: String): List<Todo> = withContext(ioDispatcher) {
        todoDao.search(query)
    }
}
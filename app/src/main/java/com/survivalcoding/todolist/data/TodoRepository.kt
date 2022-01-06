package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.data.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val todos = todoDao.selectAll()

    suspend fun upsertTodo(todo: Todo) {
        todoDao.upsert(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo)
    }
}
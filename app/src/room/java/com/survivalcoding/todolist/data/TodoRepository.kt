package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.data.model.Todo
import javax.inject.Inject

class TodoRoomRepository @Inject constructor(private val todoDao: TodoDao): TodoRepository {

    override val todos = todoDao.selectAll()

    override suspend fun upsertTodo(todo: Todo) {
        todoDao.upsert(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo)
    }
}
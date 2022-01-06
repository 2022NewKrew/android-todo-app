package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.data_source.local.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

class TodoRoomRepository(private val dao: TodoDao) {

    suspend fun getTodos(): List<Todo> = dao.getTodos()

    suspend fun getTodoById(id: Int): Todo? = dao.getTodoById(id)

    suspend fun insert(todo: Todo) = dao.insert(todo)

    suspend fun delete(todo: Todo) = dao.delete(todo)

    suspend fun update(todo: Todo) = dao.update(todo)
}
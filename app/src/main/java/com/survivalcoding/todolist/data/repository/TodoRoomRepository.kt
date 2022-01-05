package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.data_source.local.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

class TodoRoomRepository(private val dao: TodoDao) {

    fun getTodos(): List<Todo> = dao.getTodos()

    fun getTodoById(id: Int): Todo? = dao.getTodoById(id)

    fun insert(todo: Todo) = dao.insert(todo)

    fun delete(todo: Todo) = dao.delete(todo)

    fun update(todo: Todo) = dao.update(todo)
}
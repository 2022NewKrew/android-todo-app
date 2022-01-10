package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.data_source.local.TodoDao
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRoomRepository(private val dao: TodoDao): TodoRepository {

    override fun getTodos(): Flow<List<Todo>> = dao.getTodos()

    override suspend fun getTodoById(id: Int): Todo? = dao.getTodoById(id)

    override suspend fun insert(todo: Todo) = dao.insert(todo)

    override suspend fun delete(todo: Todo) = dao.delete(todo)

    override suspend fun update(todo: Todo) = dao.update(todo)
}
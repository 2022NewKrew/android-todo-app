package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.dao.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

class TodosRoomRepository(private val todoDao: TodoDao) : TodosLocalRepository {
    override suspend fun getTodos(): List<Todo> =
        todoDao.getAll().sortedWith(compareBy({ it.isDone }, { it.dueDate }, { -it.createDate }))

    override suspend fun getTodoById(id: Int): Todo = todoDao.loadById(id)
    override suspend fun addTodo(todo: Todo) = todoDao.insertAll(todo)
    override suspend fun updateTodo(todo: Todo) = todoDao.insertAll(todo)
    override suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo)
}
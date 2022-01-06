package com.survivalcoding.todolist.data.datasource

import com.survivalcoding.todolist.data.dao.TodoDao
import com.survivalcoding.todolist.domain.model.Todo

class TodosRoomRepository(private val todoDao: TodoDao) {
    suspend fun getTodos(): List<Todo> =
        todoDao.getAll().sortedWith(compareBy({ it.isDone }, { it.dueDate }, { -it.createDate }))

    suspend fun getTodoById(id: Int): Todo = todoDao.loadById(id)
    suspend fun addTodo(todo: Todo) = todoDao.insertAll(todo)
    suspend fun updateTodo(todo: Todo) = todoDao.insertAll(todo)
    suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo)
}
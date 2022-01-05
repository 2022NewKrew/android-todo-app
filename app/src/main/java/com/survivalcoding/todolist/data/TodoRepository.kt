package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.data.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val todos = todoDao.selectAll()

    fun upsertTodo(todo: Todo) {
        if (todo.id == 0) {
            insertTodo(todo)
        } else {
            updateTodo(todo)
        }
    }

    private fun insertTodo(newTodo: Todo) {
        todoDao.insert(newTodo)
    }

    private fun updateTodo(todo: Todo) {
        todoDao.update(todo)
    }

    fun deleteTodo(todo: Todo) {
        todoDao.delete(todo)
    }
}
package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.data.dao.TodoDao
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodosRepository

// 데이터 관리 공간
class TodosRepositoryImpl(private val todoDao: TodoDao) : TodosRepository {
    private var todos = todoDao.getAll()

    override fun getTodos(): List<Todo> =
        todos.sortedWith(compareBy({ it.isDone }, { it.dueDate }, { -it.createDate }))

    override fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    override fun addTodo(todo: Todo) {
        todos = todos.plus(todo)
        todoDao.insertAll(todo)
    }

    override fun updateTodo(todo: Todo) {
        todos = todos.map { origin ->
            if (origin.id == todo.id)
                todo
            else origin
        }

        todoDao.insertAll(todo)
    }

    override fun deleteTodo(todo: Todo) {
        todos = todos.minus(todo)
        todoDao.delete(todo)
    }
}
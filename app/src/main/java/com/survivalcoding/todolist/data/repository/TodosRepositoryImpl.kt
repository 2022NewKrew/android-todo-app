package com.survivalcoding.todolist.data.repository

import android.app.Application
import com.survivalcoding.todolist.data.database.AppDatabase
import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodosRepository

// 데이터 관리 공간
class TodosRepositoryImpl(application: Application) : TodosRepository {
    private val db = AppDatabase.getInstance(application)!! // 이 느낌표를 없앨 수 있는 방법은?
    private var todos = getSorted(db.todoDao().getAll())

    override fun getTodos(): List<Todo> = todos
    override fun getTodoByIndex(pos: Int): Todo = todos[pos]

    override fun addTodo(todo: Todo) {
        todos = getSorted(todos.plus(todo))
        db.todoDao().insertAll(todo)
    }

    override fun updateTodos(todo: Todo) {
        todos = getSorted(
            todos.map { origin ->
                if (origin.id == todo.id)
                    todo
                else origin
            }
        )
        db.todoDao().insertAll(todo)
    }

    override fun deleteTodo(todo: Todo) {
        todos = getSorted(todos.minus(todo))
        db.todoDao().delete(todo)
    }

    private fun getSorted(todos: List<Todo>): List<Todo> {
        return todos.sortedWith(compareBy({ it.isDone }, { it.date }))
    }
}
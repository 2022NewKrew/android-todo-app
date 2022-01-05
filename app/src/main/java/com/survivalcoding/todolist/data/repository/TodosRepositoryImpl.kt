package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo
import com.survivalcoding.todolist.domain.repository.TodosRepository
import java.util.*

// 데이터 관리 공간
class TodosRepositoryImpl : TodosRepository{
    private var todos = (1..5).map { num ->
        val today = Calendar.getInstance()
        today.add(Calendar.DATE, num)
        Todo(num.toLong(), "# $num", today.timeInMillis, "${num}번째 내용입니다")
    }
    private var nextId = todos.size + 1

    override fun getTodos(): List<Todo> = todos
    override fun getTodoByIndex(pos: Int): Todo = todos[pos]

    override fun addTodo(todo: Todo) {
        todos = todos.plus(todo.copy(id = nextId.toLong()))
        nextId += 1
    }

    override fun updateTodos(todo: Todo) {
        todos = todos.map { origin ->
            if (origin.id == todo.id)
                todo
            else origin
        }
    }

    override fun deleteTodo(pos: Int) {
        todos = todos.toMutableList().apply {
            removeAt(pos)
        }
    }
}
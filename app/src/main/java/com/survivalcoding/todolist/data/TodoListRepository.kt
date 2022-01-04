package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.domain.model.Todo
import java.util.*

// 데이터 관리 공간
object TodoListRepository {
    private var todos = (1..5).map { num ->
        val today = Calendar.getInstance()
        today.add(Calendar.DATE, num)
        Todo(num.toLong(), "# $num", today.timeInMillis, "${num}번째 내용입니다")
    }

    fun getTodos(): List<Todo> = todos
    fun getTodoByIndex(pos: Int): Todo = todos[pos]

    fun addTodo(todo: Todo) {
        todos = todos.plus(todo)
    }

    fun updateTodo(todo: Todo) {
        todos = todos.map { origin ->
            if (origin.id == todo.id)
                todo
            else origin
        }
    }

    fun deleteTodo(pos: Int) {
        //todo: 삭제 구현
        todos = todos.minus(getTodoByIndex(pos))
    }
}
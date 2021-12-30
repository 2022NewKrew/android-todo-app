package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo
import java.util.*

class TodoRepository {
    var todos = (1..30).map { num ->
        val today = Calendar.getInstance()
        today.add(Calendar.DATE, num)
        Todo(num.toLong(), "# $num", today.timeInMillis, "${num}번째 내용입니다")
    }

    fun updateTodos(todo: Todo){
        todos = todos.toMutableList().map { origin ->
            if (origin.id == todo.id) origin.copy(isDone = !origin.isDone)
            else origin
        }
    }
}
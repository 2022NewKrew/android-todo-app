package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo

class TodoRepository {
    // 변경 안되는 리스트
    var todos = (0..30).map { num ->
        Todo(
            id = num.toLong(),
            title = "청소 $num",
        )
    }.toList()

    fun updateTodo(todo: Todo) {
        todos = todos.toMutableList().map {
            if (it.id == todo.id) {
                todo.copy(isDone = !todo.isDone)
            } else {
                it
            }
        }
    }
}
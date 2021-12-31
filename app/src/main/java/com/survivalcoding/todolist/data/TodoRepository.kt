package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo
import java.util.*

// 데이터 관리 공간
object TodoRepository {
    var todos = (1..30).map { num ->
        val today = Calendar.getInstance()
        today.add(Calendar.DATE, num)
        Todo(num.toLong(), "# $num", today.timeInMillis, "${num}번째 내용입니다")
    } // todos 미리 정의

    //todo를 업데이트 하는 방법
    fun updateIsDone(todo: Todo){
        todos = todos.toMutableList().map { origin ->
            if (origin.id == todo.id) origin.copy(isDone = !origin.isDone)
            else origin
        }
    }

    fun updateTodo(todo: Todo){
        todos = todos.toMutableList().map { origin ->
            if (origin.id == todo.id) origin.copy(title = todo.title, date = todo.date, content = todo.content)
            else origin
        }
    }

    //todo를 업데이트 하는 방법
    fun addTodo(todo: Todo){
        todos = todos.plus(todo)
    }
}
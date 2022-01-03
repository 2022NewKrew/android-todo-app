package com.survivalcoding.todolist.data

import com.survivalcoding.todolist.model.Todo

class TodoRepository {
    var todoList: List<Todo> =
        (0..30).map { num -> Todo(id = num.toLong(), title = "할 일 $num") }.toList()

    fun updateList(todo: Todo) {
        todoList = todoList.toMutableList().map {
            if (it.id == todo.id) todo.copy(isDone = !todo.isDone)
            else it
        }
    }

    fun addItem(todo: Todo) {
        val newList = todoList.toMutableList()
        newList.add(todo)
        todoList = newList.toList()
    }

    fun updateItem(todo: Todo) {
        todoList = todoList.toMutableList().map {
            if (it.id == todo.id) todo
            else it
        }
    }

    fun deleteItem(todo: Todo) {
        val newList = todoList.toMutableList()
        newList.remove(todo)
        todoList = newList.toList()
    }
}
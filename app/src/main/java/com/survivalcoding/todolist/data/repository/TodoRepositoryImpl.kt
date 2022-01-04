package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.repository.TodoRepository
import com.survivalcoding.todolist.model.Todo

class TodoRepositoryImpl: TodoRepository {
    var todoList: List<Todo> =
        (0..30).map { num -> Todo(id = num.toLong(), title = "할 일 $num") }.toList()

    override fun addItem(todo: Todo) {
        val newList = todoList.toMutableList()
        newList.add(todo)
        todoList = newList.toList()
    }

    override fun updateItem(todo: Todo) {
        todoList = todoList.toMutableList().map {
            if (it.id == todo.id) todo
            else it
        }
    }

    override fun deleteItem(todo: Todo) {
        val newList = todoList.toMutableList()
        newList.remove(todo)
        todoList = newList.toList()
    }
}
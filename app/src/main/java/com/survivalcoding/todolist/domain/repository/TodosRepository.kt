package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodosRepository {
    fun getTodos(): List<Todo>
    fun getTodoByIndex(pos: Int): Todo
    fun addTodo(todo: Todo)
    fun updateTodos(todo: Todo)
    fun deleteTodo(todo: Todo)
}
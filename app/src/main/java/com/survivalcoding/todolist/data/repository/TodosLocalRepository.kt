package com.survivalcoding.todolist.data.repository

import com.survivalcoding.todolist.domain.model.Todo

interface TodosLocalRepository {
    fun getTodos(): List<Todo>
    fun getTodoById(id: Int): Todo?
    fun addTodo(todo: Todo)
    fun updateTodo(todo: Todo)
    fun deleteTodo(todo: Todo)
}
package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.entity.Todo

interface TodoRepository {
    fun getTodos(): List<Todo>
    fun upDateIsDone(oldItem: Todo)
    fun insert(title: String)
}
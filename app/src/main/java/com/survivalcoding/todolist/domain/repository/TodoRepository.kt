package com.survivalcoding.todolist.domain.repository

import com.survivalcoding.todolist.domain.entity.Todo

interface TodoRepository {
    fun getTodos(): List<Todo>
    fun upDateIsDone(oldItem: Todo)
    fun upDateTitle(title: String, id: Long)
    fun insert(title: String)
}
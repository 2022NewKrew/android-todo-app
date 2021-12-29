package com.survivalcoding.todolist.model

data class Todo(
    val id: Long,
    val title: String,
    val isDone: Boolean = false,
)

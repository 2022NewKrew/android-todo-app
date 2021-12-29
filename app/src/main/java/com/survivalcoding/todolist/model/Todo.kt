package com.survivalcoding.todolist.model

import java.util.*

data class Todo(
    val id: Long,
    val title: String,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false,
)
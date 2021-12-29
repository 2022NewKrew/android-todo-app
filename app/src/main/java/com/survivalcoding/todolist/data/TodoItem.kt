package com.survivalcoding.todolist.data

import java.util.*

data class TodoItem(
    val id: Long,
    val title: String,
    val content: String?,
    val timestamp: Long? = Date().time,
    val isDone: Boolean
)
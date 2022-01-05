package com.survivalcoding.todolist.data

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["id"], tableName = "TODO_TB")
data class TodoModel(
    val id: Long,
    val title: String,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false,
)
package com.survivalcoding.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "TODO_TB")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false,
)
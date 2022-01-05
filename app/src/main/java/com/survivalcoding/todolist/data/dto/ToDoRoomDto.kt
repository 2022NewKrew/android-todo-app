package com.survivalcoding.todolist.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ToDoRoomDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "timeStamp")
    val timeStamp: Long = Date().time,
    @ColumnInfo(name = "isDone")
    val isDone: Boolean = false,
)
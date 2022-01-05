package com.survivalcoding.todolist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long = -1L,
    val title: String,
    val content: String,
    val isDone: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(),
) : Parcelable

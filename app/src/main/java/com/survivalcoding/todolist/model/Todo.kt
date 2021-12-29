package com.survivalcoding.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    val title: String,
    val date: Long,
    val content: String,
    val isDone: Boolean = false
) : Parcelable

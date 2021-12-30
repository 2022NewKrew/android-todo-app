package com.survivalcoding.todolist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TodoItem(
    val id: Long,
    val title: String,
    val description: String?,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false
) : Parcelable
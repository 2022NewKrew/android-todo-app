package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Todo(
    val id: Int,
    val title: String,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false,
) : Parcelable
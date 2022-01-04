package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Todo(
    val id: Long = -1,
    val title: String = "",
    val date: Long = Date().time,
    val content: String = "",
    val isDone: Boolean = false
) : Parcelable

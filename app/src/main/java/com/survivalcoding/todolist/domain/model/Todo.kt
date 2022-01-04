package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    val title: String,
    val isDone: Boolean = false,
) : Parcelable

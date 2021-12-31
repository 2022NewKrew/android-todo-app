package com.survivalcoding.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Long,
    val taskName: String,
    val date: String,
    val isDone: Boolean = false,
) : Parcelable
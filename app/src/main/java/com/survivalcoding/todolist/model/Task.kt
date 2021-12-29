package com.survivalcoding.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Long,
    var taskName: String,
    var date: String,
    var isDone: Boolean = false,
) : Parcelable
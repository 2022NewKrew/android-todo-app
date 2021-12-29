package com.survivalcoding.todolist.model


import android.os.Parcelable
import java.util.*
import kotlinx.parcelize.Parcelize


@Parcelize
data class Todo(
    val id: Long,
    val title: String,
    val timestamp: Long = Date().time,
    var isDone: Boolean = false,
) : Parcelable


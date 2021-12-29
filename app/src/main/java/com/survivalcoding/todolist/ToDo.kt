package com.survivalcoding.todolist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ToDo(
    val id: Long,
    val title: String,
    val timeStamp: Long = Date().time,
    val isDone: Boolean = false,
) : Parcelable

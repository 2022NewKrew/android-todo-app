package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ToDo(
    val id: Long = 0,
    val title: String,
    val timeStamp: Long = Date().time,
    val isDone: Boolean = false,
) : Parcelable

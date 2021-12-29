package com.survivalcoding.todolist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val title: String,
    val content: String,
    val timestamp: Long,
    val hasHighlight: Boolean,
): Parcelable

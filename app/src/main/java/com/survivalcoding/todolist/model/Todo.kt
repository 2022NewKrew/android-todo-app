package com.survivalcoding.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    val title: String,
    val content: String,
    val timestamp: Long,
    val hasHighlight: Boolean,
): Parcelable

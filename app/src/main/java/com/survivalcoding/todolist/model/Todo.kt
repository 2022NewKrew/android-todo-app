package com.survivalcoding.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(val title: String, val date: Long, val content: String) : Parcelable

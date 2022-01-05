package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class Todo(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val timestamp: Long = Date().time,
    val isDone: Boolean = false,
) : Parcelable
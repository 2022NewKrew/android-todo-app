package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val date: Long = Date().time,
    val content: String = "",
    val isDone: Boolean = false
) : Parcelable

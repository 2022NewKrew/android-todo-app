package com.survivalcoding.todolist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Todo(
    @PrimaryKey val id: Int?,
    val title: String,
    val isDone: Boolean = false,
) : Parcelable

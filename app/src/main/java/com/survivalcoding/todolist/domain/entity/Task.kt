package com.survivalcoding.todolist.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Task(
    @PrimaryKey val id: Long,
    val taskName: String,
    val taskInfo: String,
    val date: Long,
    val isDone: Boolean = false,
    val isExpanded: Boolean = false,
) : Parcelable

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
    val createDate: Long = Date().time,
    val dueDate: Long = createDate - createDate % 86400000, // hour 이전 데이터 삭제 위한 야매 방법
    val content: String = "",
    val isDone: Boolean = false
) : Parcelable

package com.survivalcoding.todolist

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTimestampString(): String {
    return SimpleDateFormat("yyyy.MM.dd HH:mm").format(Date(this))
}
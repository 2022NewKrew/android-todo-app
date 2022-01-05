package com.survivalcoding.todolist.ui.main.adapter

import com.survivalcoding.todolist.domain.entity.Task

interface OnClickEvent {
    fun clickEvent(task: Task)
    fun longClickEvent(task: Task): Boolean
}
package com.survivalcoding.todolist.ui.main.adapter

import com.survivalcoding.todolist.domain.entity.Task

interface OnClickEvent {
    fun isDoneClickEvent(task: Task)
    fun longClickEvent(task: Task): Boolean
    fun expandClickEvent(task: Task)
}
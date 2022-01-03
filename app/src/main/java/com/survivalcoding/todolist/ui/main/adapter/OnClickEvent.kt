package com.survivalcoding.todolist.ui.main.adapter

interface OnClickEvent {
    fun clickEvent(id: Long)
    fun longClickEvent(id: Long): Boolean
}
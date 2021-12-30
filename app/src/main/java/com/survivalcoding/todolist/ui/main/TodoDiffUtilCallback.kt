package com.survivalcoding.todolist.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.data.model.TodoItem

object TodoDiffUtilCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem) = oldItem == newItem
}
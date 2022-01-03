package com.survivalcoding.todolist.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.domain.entity.Task

object DiffUtilCallBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
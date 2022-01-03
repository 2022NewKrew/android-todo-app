package com.survivalcoding.todolist.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.domain.model.Todo

object TodoDiffItemCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

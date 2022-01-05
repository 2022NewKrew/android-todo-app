package com.survivalcoding.todolist.presentation.main.todolist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.domain.model.ToDo

class ToDoDiffUtilItemCallback: DiffUtil.ItemCallback<ToDo>() {

    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem
}
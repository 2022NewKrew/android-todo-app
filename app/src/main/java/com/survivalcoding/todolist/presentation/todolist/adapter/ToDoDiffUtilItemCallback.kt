package com.survivalcoding.todolist.presentation.todolist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.domain.model.ToDo

class ToDoDiffUtilItemCallback: DiffUtil.ItemCallback<ToDo>() {

    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem === newItem

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem
}
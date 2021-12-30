package com.survivalcoding.todolist.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.todolist.ToDo

class ToDoDiffUtilItemCallback: DiffUtil.ItemCallback<ToDo>() {

    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem === newItem

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem
}
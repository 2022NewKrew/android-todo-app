package com.survivalcoding.todolist.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ListItemBinding

class ToDoListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ListItemBinding.bind(itemView)
    val nameTextView = binding.taskNameTextView
    val dateTextView = binding.taskDateTextView
    val background = binding.itemBackground
}
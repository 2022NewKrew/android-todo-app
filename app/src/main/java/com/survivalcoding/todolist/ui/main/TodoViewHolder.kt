package com.survivalcoding.todolist.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.databinding.TodoListItemBinding

class TodoViewHolder(val binding: TodoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(todoItem: TodoItem, checkChanged: (Long) -> Unit) {
        binding.textViewTitle.text = todoItem.title
        binding.textViewDescription.text = todoItem.description ?: ""
        binding.textViewTimestamp.text =
            todoItem.timestamp.toString() // TODO: 2021/12/29 convert to date format
        binding.checkboxIsDone.isChecked = todoItem.isDone
        binding.checkboxIsDone.setOnClickListener {
            checkChanged(todoItem.id)
        }
    }
}
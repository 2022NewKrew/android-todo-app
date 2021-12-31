package com.survivalcoding.todolist.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo

class TodoViewHolder(
    private val binding: ItemTodoBinding,
    private val itemClickListener: (Todo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todo: Todo) {
        binding.todoCheckbox.isChecked = todo.isDone
        binding.todoCheckbox.text = todo.title

        binding.todoCheckbox.setOnClickListener { itemClickListener(todo) }
    }
}
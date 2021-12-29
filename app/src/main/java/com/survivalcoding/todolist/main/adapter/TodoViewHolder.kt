package com.survivalcoding.todolist.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo

class TodoViewHolder(
    parent: ViewGroup,
    val onItemClicked: (Todo) -> Unit,
) {
    val binding = ItemTodoBinding.bind(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
    )

    init {
        binding.root.tag = this
    }

    fun bind(todo: Todo) {
        binding.titleTextView.text = todo.title

        if (todo.isDone) {
            binding.root.setBackgroundColor(Color.RED)
        } else {
            binding.root.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.root.setOnClickListener {
          onItemClicked(todo)
        }
    }
}
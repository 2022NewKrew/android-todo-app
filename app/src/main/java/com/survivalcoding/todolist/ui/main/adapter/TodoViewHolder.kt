package com.survivalcoding.todolist.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.domain.entity.Todo

class TodoViewHolder(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {

    fun bind(
        item: Todo,
        position: Int,
        onItemClicked: (Int) -> Unit,
    ) {
        binding.todoTextview.text = item.title
        if (item.isDone) binding.todoCardView.setBackgroundColor(Color.RED)
        else binding.todoCardView.setBackgroundColor(Color.TRANSPARENT)

        binding.todoTextview.setOnClickListener {
            onItemClicked(position)
        }
    }

    companion object {
        fun builder(parent: ViewGroup): TodoViewHolder =
            TodoViewHolder(
                ItemTodoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }


}


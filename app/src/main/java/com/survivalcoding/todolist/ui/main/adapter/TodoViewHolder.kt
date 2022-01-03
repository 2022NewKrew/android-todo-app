package com.survivalcoding.todolist.ui.main.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.domain.entity.Todo

class TodoViewHolder(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {

    fun bind(
        item: Todo,
        onItemClicked: (Todo) -> Unit,
    ) {
        binding.todoTextview.text = item.title
        Log.d(item.title, item.isDone.toString())
        if (item.isDone) binding.todoCardView.setCardBackgroundColor(Color.RED)
        else binding.todoCardView.setCardBackgroundColor(Color.WHITE)
        binding.todoTextview.setOnClickListener {
            onItemClicked(item)
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


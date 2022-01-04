package com.survivalcoding.todolist.ui.main.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.domain.entity.Todo
import java.sql.Timestamp
import java.text.SimpleDateFormat

class TodoViewHolder(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(
        item: Todo,
        onItemClicked: (Todo) -> Unit,
    ) {
        binding.todoTextview.text = item.title

        val pattern = "yyyy-MM-dd HH:mm";
        val formatter = SimpleDateFormat(pattern)
        val date = "until : " + formatter.format(Timestamp(item.timestamp));

        binding.deadlineTextview.text = date
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


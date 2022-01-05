package com.survivalcoding.todolist.ui.main.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.domain.entity.Todo
import java.sql.Timestamp
import java.text.SimpleDateFormat

class TodoViewHolder(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {

    fun bind(
        item: Todo,
        onItemClicked: (Todo) -> Unit,
        onLongClicked: (Todo) -> Unit
    ) {
        binding.todoTextview.text = item.title

        val pattern = "yyyy-MM-dd HH:mm";
        val formatter = SimpleDateFormat(pattern)
        val date = "set : " + formatter.format(item.timestamp);

        binding.deadlineTextview.text = date
        if (item.isDone) {
            binding.todoTextview.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.todoTextview.setTextColor(Color.GRAY)
        } else {
            binding.todoTextview.paintFlags = 0
            binding.todoTextview.setTextColor(Color.BLACK)
        }
        binding.root.setOnClickListener {
            onItemClicked(item)
        }
        binding.root.setOnLongClickListener {
            onLongClicked(item)
            true
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


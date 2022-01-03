package com.survivalcoding.todolist.presentation.main.adapter

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo

class TodoViewHolder(
    private val binding: ItemTodoBinding,
    private val checkClickListener: (Todo) -> Unit,
    private val itemClickListener: (Todo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todo: Todo) {
        binding.todoTvTitle.text = todo.title

        if (todo.isDone) {
            binding.todoIvCheck.setImageResource(R.drawable.ic_checked)
            binding.todoTvTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.todoTvTitle.setTextColor(binding.todoTvTitle.context.getColor(R.color.gray))
        } else {
            binding.todoIvCheck.setImageResource(R.drawable.ic_unchecked)
            binding.todoTvTitle.paintFlags = 0
            binding.todoTvTitle.setTextColor(binding.todoTvTitle.context.getColor(R.color.black))
        }

        binding.todoIvCheck.setOnClickListener { checkClickListener(todo) }
        binding.root.setOnClickListener { itemClickListener(todo) }
    }
}
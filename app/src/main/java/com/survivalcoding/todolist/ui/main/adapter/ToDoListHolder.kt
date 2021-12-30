package com.survivalcoding.todolist.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ListItemBinding
import com.survivalcoding.todolist.model.Task

class ToDoListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
) {
    private val binding = ListItemBinding.bind(itemView)

    fun binding(
        currentTask: Task,
        clickEvent: (id: Long) -> Unit
    ) {
        binding.taskNameTextView.text = currentTask.taskName
        binding.taskDateTextView.text = currentTask.date

        if (currentTask.isDone)
            binding.itemBackground.setBackgroundResource(R.drawable.shape_selected_to_do_item)
        else
            binding.itemBackground.setBackgroundResource(R.drawable.shape_to_do_item)

        itemView.setOnClickListener {
            clickEvent(currentTask.id)
        }
    }
}
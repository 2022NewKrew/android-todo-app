package com.survivalcoding.todolist.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ListItemBinding
import com.survivalcoding.todolist.model.Task

class ToDoListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListItemBinding.bind(itemView)
    private val nameTextView = binding.taskNameTextView
    private val dateTextView = binding.taskDateTextView
    private val background = binding.itemBackground

    fun binding(position:Int, currentTask: Task, clickEvent: (position: Int, tasks: Task) -> Unit) {
        nameTextView.text = currentTask.taskName
        dateTextView.text = currentTask.date

        if (currentTask.isDone)
            background.setBackgroundResource(R.drawable.shape_selected_to_do_item)
        else
            background.setBackgroundResource(R.drawable.shape_to_do_item)

        itemView.setOnClickListener {
            clickEvent(position, currentTask)
        }
    }
}
package com.survivalcoding.todolist.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ListItemBinding
import com.survivalcoding.todolist.domain.entity.Task
import java.text.SimpleDateFormat
import java.util.*

class ToDoListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
) {
    private val binding = ListItemBinding.bind(itemView)

    fun binding(
        currentTask: Task,
        clickEvent: OnClickEvent
    ) {
        binding.taskNameTextView.text = currentTask.taskName
        binding.taskDateTextView.text = if (isToday(currentTask.date)) "today" else currentTask.date

        if (currentTask.isDone)
            binding.itemBackground.setBackgroundResource(R.drawable.shape_selected_to_do_item)
        else
            binding.itemBackground.setBackgroundResource(R.drawable.shape_to_do_item)

        itemView.setOnClickListener {
            clickEvent.clickEvent(currentTask.id)
        }

        itemView.setOnLongClickListener {
            clickEvent.longClickEvent(currentTask.id)
        }
    }

    private fun isToday(date: String): Boolean {
        return date == SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}
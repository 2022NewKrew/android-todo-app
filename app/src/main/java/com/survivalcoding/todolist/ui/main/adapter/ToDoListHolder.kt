package com.survivalcoding.todolist.ui.main.adapter

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
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
        binding.tvTaskName.text = currentTask.taskName
        binding.tvTaskDate.text = if (isToday(currentTask.date)) "today" else currentTask.date

        if (currentTask.isDone) {
//            binding.itemBackground.setBackgroundResource(R.drawable.shape_selected_to_do_item)
            binding.ivCheckButton.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
            binding.tvTaskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvTaskName.setTextColor(Color.parseColor("#DBE2EF"))
            binding.tvTaskName.setTypeface(binding.tvTaskName.typeface, Typeface.ITALIC)

            binding.tvTaskDate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvTaskDate.setTextColor(Color.parseColor("#DBE2EF"))
        } else {
//            binding.itemBackground.setBackgroundResource(R.drawable.shape_to_do_item)
            binding.ivCheckButton.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
            binding.tvTaskName.paintFlags = 0
            binding.tvTaskName.setTextColor(Color.parseColor("#000000"))
            binding.tvTaskName.typeface = Typeface.DEFAULT

            binding.tvTaskDate.paintFlags = 0
            binding.tvTaskDate.setTextColor(Color.parseColor("#000000"))
        }


        binding.ivCheckButton.setOnClickListener {
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
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
        binding.tvTaskDate.text = displayDate(currentTask.date)

        if (currentTask.isDone) {
            binding.ivCheckButton.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)

            binding.tvTaskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvTaskName.setTextColor(Color.parseColor(IS_DONE_COLOR))
            binding.tvTaskName.setTypeface(binding.tvTaskName.typeface, Typeface.ITALIC)

            binding.tvTaskDate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvTaskDate.setTextColor(Color.parseColor(IS_DONE_COLOR))

            binding.tvTaskInfo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvTaskInfo.setTextColor(Color.parseColor(IS_DONE_COLOR))
            binding.tvTaskInfo.setTypeface(binding.tvTaskName.typeface, Typeface.ITALIC)
        } else {
            binding.ivCheckButton.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)

            binding.tvTaskName.paintFlags = 0
            binding.tvTaskName.setTextColor(Color.parseColor(IS_NOT_DONE_NAME_COLOR))
            binding.tvTaskName.typeface = Typeface.DEFAULT

            binding.tvTaskDate.paintFlags = 0
            binding.tvTaskDate.setTextColor(Color.parseColor(IS_NOT_DONE_INFO_COLOR))

            binding.tvTaskInfo.paintFlags = 0
            binding.tvTaskInfo.setTextColor(Color.parseColor(IS_NOT_DONE_INFO_COLOR))
            binding.tvTaskInfo.typeface = Typeface.DEFAULT
        }

        binding.tvTaskInfo.text = currentTask.taskInfo

        if (currentTask.isExpanded) {
            binding.tvTaskInfo.maxLines = Int.MAX_VALUE
            binding.tvTaskName.maxLines = Int.MAX_VALUE
        } else {
            binding.tvTaskInfo.maxLines = 1
            binding.tvTaskName.maxLines = 1
        }

        binding.ivCheckButton.setOnClickListener {
            clickEvent.clickEvent(currentTask)
        }

        binding.tvTaskInfo.setOnClickListener {
            clickEvent.expandClickEvent(currentTask)
        }

        binding.tvTaskName.setOnClickListener {
            clickEvent.expandClickEvent(currentTask)
        }

        itemView.setOnLongClickListener {
            clickEvent.longClickEvent(currentTask)
        }

        binding.ivCheckButton.setOnLongClickListener { clickEvent.longClickEvent(currentTask) }
        binding.tvTaskName.setOnLongClickListener { clickEvent.longClickEvent(currentTask) }
        binding.tvTaskInfo.setOnLongClickListener { clickEvent.longClickEvent(currentTask) }
        binding.tvTaskDate.setOnLongClickListener { clickEvent.longClickEvent(currentTask) }
    }

    private fun displayDate(dateTime: Long): String {
        return when (val timeGap = ((Date().time - dateTime) / 864000000)) {
            0L -> "today"
            1L -> "yesterday"
            else -> "$timeGap days ago"
        }
    }

    companion object {
        const val IS_DONE_COLOR = "#DBE2EF"
        const val IS_NOT_DONE_NAME_COLOR = "#112D4E"
        const val IS_NOT_DONE_INFO_COLOR = "#3F72AF"
    }
}
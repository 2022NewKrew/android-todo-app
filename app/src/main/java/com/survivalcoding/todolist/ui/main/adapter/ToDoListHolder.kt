package com.survivalcoding.todolist.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ListItemBinding
import com.survivalcoding.todolist.model.Task
import java.text.SimpleDateFormat
import java.util.*

class ToDoListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
) {
    private val binding = ListItemBinding.bind(itemView)

    fun binding(
        currentTask: Task,
        clickEvent: (id: Long) -> Unit,
        longClickEvent: (id: Long) -> Boolean,
    ) {
        binding.taskNameTextView.text = currentTask.taskName
        binding.taskDateTextView.text = if (isToday(currentTask.date)) "today" else currentTask.date

        /*
         * toggle 했을 때, 뷰홀더의 레이아웃을 변경하고 싶은데 방법을 못찾음.
         * 리사이클러뷰 - 어댑터 - 뷰홀더가 연결되어 있어서
         * 뷰홀더에서 레이아웃 변경, 어댑터에서 뷰홀더 변경, 리사이클러뷰에서 어댑터 변경 모두 안될것같음.
         */
        if (currentTask.isDone)
            binding.itemBackground.setBackgroundResource(R.drawable.shape_selected_to_do_item)
        else
            binding.itemBackground.setBackgroundResource(R.drawable.shape_to_do_item)

        itemView.setOnClickListener {
            clickEvent(currentTask.id)
        }

        itemView.setOnLongClickListener {
            longClickEvent(currentTask.id)
        }
    }

    private fun isToday(date: String): Boolean {
        return date == SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}
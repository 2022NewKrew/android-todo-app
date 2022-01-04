package com.survivalcoding.todolist.presentation.main.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.domain.model.Todo
import java.text.SimpleDateFormat
import java.util.*

class TodoViewHolder(
    itemView: View,
    val onClickCheckBox: (Todo) -> Unit,
    val onClickViewShort: (Int) -> Unit,
    val onClickViewLong: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTodoBinding.bind(itemView)

    fun bind(todo: Todo) { // 실제로 뷰홀더 내에서 그려주는 함수
        binding.titleText.text = todo.title
        binding.timeText.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(todo.date)
        binding.contentText.text = todo.content
        binding.isDone.isChecked = todo.isDone

        if (todo.isDone) {
            binding.titleText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.contentText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.root.setBackgroundColor(Color.GRAY)
        } else {
            binding.titleText.paintFlags = 0
            binding.contentText.paintFlags = 0
            binding.root.setBackgroundColor(Color.parseColor("#96D1FF"))
        }

        binding.isDone.setOnClickListener {
            onClickCheckBox(todo)
        }

        itemView.setOnClickListener {
            onClickViewShort(adapterPosition)
        }

        binding.root.setOnLongClickListener {
            onClickViewLong(adapterPosition)
            true
        }
    }
}
package com.survivalcoding.todolist.presentation.main.adapter

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
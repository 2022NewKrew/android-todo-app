package com.survivalcoding.todolist.presentation.main.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo
import java.text.SimpleDateFormat
import java.util.*

class TodoViewHolder(
    itemView: View,
    val onChangeIsDone: (Todo) -> Unit,
    val onModifyTodo: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTodoBinding.bind(itemView)

    fun bind(todo: Todo) { // 실제로 뷰홀더 내에서 그려주는 함수
        binding.titleText.text = todo.title
        binding.timeText.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(todo.date)
        binding.contentText.text = todo.content
        binding.isDone.isChecked = todo.isDone

        binding.isDone.setOnClickListener {
            onChangeIsDone(todo)
        }

        itemView.setOnClickListener {
            onModifyTodo(adapterPosition)
        }
    }
}
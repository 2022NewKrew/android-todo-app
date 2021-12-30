package com.survivalcoding.todolist.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo
import java.text.SimpleDateFormat
import java.util.*

class TodoViewHolder(
    itemView: View,
    val onItemClicked: (Todo) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTodoBinding.bind(itemView)

    fun bind(todo: Todo) { // 실제로 뷰홀더 내에서 그려주는 함수
        binding.titleText.text = todo.title
        binding.timeText.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(todo.date)
        binding.contentText.text = todo.content

        if (todo.isDone) binding.root.setBackgroundColor(Color.RED)
        else binding.root.setBackgroundColor(Color.TRANSPARENT)

        itemView.setOnClickListener {
            onItemClicked(todo)
        }
    }
}
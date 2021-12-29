package com.survivalcoding.todolist.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo
import java.text.SimpleDateFormat
import java.util.*

class TodoViewHolder(
    parent: ViewGroup,
    val onItemClicked: (Todo) -> Unit
) {
    val binding = ItemTodoBinding.bind(
        LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
    )

    //binding.root는 layout을 가져옴, 그래서 tag도 붙일 수 있음
    init{
        binding.root.tag = this
    }

    fun bind(todo: Todo){ // 실제로 뷰홀더 내에서 그려주는 함수
        binding.titleText.text = todo.title
        binding.timeText.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(todo.date)
        binding.contentText.text = todo.content

        if(todo.isDone) binding.root.setBackgroundColor(Color.RED)
        else binding.root.setBackgroundColor(Color.TRANSPARENT)

        binding.root.setOnClickListener {
            onItemClicked(todo)
        }
    }
}
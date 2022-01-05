package com.survivalcoding.todolist.ui.main.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.model.Todo
import com.survivalcoding.todolist.databinding.ListItemTodoBinding

class TodoViewHolder(private val listItemTodoBinding: ListItemTodoBinding) :
    RecyclerView.ViewHolder(listItemTodoBinding.root) {

    fun bind(todo: Todo, onClick: (Int) -> Unit) {
        listItemTodoBinding.apply {
            todoListItemTitleTv.text = todo.title
            root.setBackgroundColor(if (todo.isDone) Color.RED else Color.TRANSPARENT)
            root.setOnClickListener { onClick(todo.id) }
        }
    }
}
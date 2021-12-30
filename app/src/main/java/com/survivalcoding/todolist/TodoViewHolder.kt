package com.survivalcoding.todolist

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ListItemTodoBinding

class TodoViewHolder(private val listItemTodoBinding: ListItemTodoBinding) :
    RecyclerView.ViewHolder(listItemTodoBinding.root) {

    fun bind(todo: Todo, onClick: (Long) -> Unit) {
        listItemTodoBinding.apply {
            todoListItemTitleTv.text = todo.title
            root.setBackgroundColor(if(todo.hasHighlight) Color.RED else Color.TRANSPARENT)
            root.setOnClickListener { onClick(todo.id) }
        }
    }
}
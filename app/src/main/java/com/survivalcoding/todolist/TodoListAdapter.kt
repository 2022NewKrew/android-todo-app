package com.survivalcoding.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.databinding.ListItemTodoBinding

class TodoListAdapter(private val onClick: TodoListAdapter.(Long) -> Unit) :
    ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ListItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position)) { onClick(it) }
    }
}
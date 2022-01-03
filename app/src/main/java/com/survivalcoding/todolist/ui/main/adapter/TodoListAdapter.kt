package com.survivalcoding.todolist.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.domain.entity.Todo

class TodoListAdapter(private val onItemClicked: (Todo) -> Unit) :
    ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }
}
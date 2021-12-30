package com.survivalcoding.todolist.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.domain.entity.Todo

class TodoListAdapter : ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {
    var onItemClicked: (Int) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClicked)
    }
}
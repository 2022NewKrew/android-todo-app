package com.survivalcoding.todolist.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.model.Todo

class TodoListAdapter : ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {
    var onItemClicked: (Todo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

package com.survivalcoding.todolist.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.model.Task

class ToDoListAdapter() : ListAdapter<Task, ToDoListHolder>(DiffUtilCallBack) {
    var clickEvent: (id: Long) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListHolder {
        return ToDoListHolder(parent)
    }

    override fun onBindViewHolder(holder: ToDoListHolder, position: Int) {
        holder.binding(getItem(position), clickEvent)
    }
}


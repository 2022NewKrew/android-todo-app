package com.survivalcoding.todolist.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.domain.entity.Task

class ToDoListAdapter(
    private val clickEvent: OnClickEvent
) : ListAdapter<Task, ToDoListHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListHolder {
        return ToDoListHolder(parent)
    }

    override fun onBindViewHolder(holder: ToDoListHolder, position: Int) {
        holder.binding(getItem(position), clickEvent)
    }
}


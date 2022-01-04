package com.survivalcoding.todolist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.databinding.TodoListItemBinding

class TodoAdapter(
    private val checkChanged: (Long) -> Unit
) : ListAdapter<TodoItem, TodoViewHolder>(TodoDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding =
            TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), checkChanged)
    }
}
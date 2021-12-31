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

class TodoViewHolder(val binding: TodoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(todoItem: TodoItem, checkChanged: (Long) -> Unit) {
        binding.textViewTitle.text = todoItem.title
        binding.textViewDescription.text = todoItem.description ?: ""
        binding.textViewTimestamp.text =
            todoItem.timestamp.toString() // TODO: 2021/12/29 convert to date format
        binding.checkboxIsDone.isChecked = todoItem.isDone
        binding.checkboxIsDone.setOnClickListener {
            checkChanged(todoItem.id)
        }
    }
}
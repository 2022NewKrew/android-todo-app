package com.survivalcoding.todolist.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.data.TodoItem
import com.survivalcoding.todolist.databinding.TodoListItemBinding

class TodoAdapter(
    var data: List<TodoItem>, val checkChanged: (Long) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: TodoListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding =
            TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        with(holder) {
            with(data[position]) {
                binding.textViewTitle.text = this.title
                binding.textViewDescription.text = this.description ?: ""
                binding.textViewTimestamp.text =
                    this.timestamp?.toString() // TODO: 2021/12/29 convert to date format
                binding.checkboxIsDone.isChecked = this.isDone
                binding.checkboxIsDone.setOnClickListener {
                    checkChanged(this.id)
                }
            }
        }
    }

    override fun getItemCount() = data.size
}
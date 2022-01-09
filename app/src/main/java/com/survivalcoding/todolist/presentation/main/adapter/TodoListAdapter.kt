package com.survivalcoding.todolist.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.domain.model.Todo

class TodoListAdapter(
    private val onClickCheckBox: (Todo) -> Unit,
    private val onClickViewShort: (Todo) -> Unit,
    private val onSwipedLeft: (Todo) -> Unit,
) : ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view, onClickCheckBox, onClickViewShort)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(position: Int) {
        onSwipedLeft(getItem(position))
    }
}

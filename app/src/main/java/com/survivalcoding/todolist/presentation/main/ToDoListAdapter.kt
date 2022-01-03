package com.survivalcoding.todolist.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.domain.model.ToDo

class ToDoListAdapter(
    private val onItemCheckedChanged: (ToDo, Boolean) -> Unit,
    private val onDeleteButtonClick: (ToDo) -> Unit
) : ListAdapter<ToDo, ToDoViewHolder>(ToDoDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ToDoViewHolder.from(parent)

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int): Unit =
        holder.bind(getItem(position), onItemCheckedChanged, onDeleteButtonClick)
}

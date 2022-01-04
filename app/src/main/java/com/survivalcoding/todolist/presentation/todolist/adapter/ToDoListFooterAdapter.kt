package com.survivalcoding.todolist.presentation.todolist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListFooterAdapter : RecyclerView.Adapter<ToDoFooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ToDoFooterViewHolder.from(parent)

    override fun onBindViewHolder(holder: ToDoFooterViewHolder, position: Int) {}

    override fun getItemCount() = 1
}
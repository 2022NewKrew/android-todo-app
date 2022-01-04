package com.survivalcoding.todolist.presentation.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ToDoListFooterLayoutBinding

class ToDoFooterViewHolder(
    private val binding: ToDoListFooterLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ToDoFooterViewHolder {
            return ToDoFooterViewHolder(
                ToDoListFooterLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
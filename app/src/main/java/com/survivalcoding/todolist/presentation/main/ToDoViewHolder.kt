package com.survivalcoding.todolist.presentation.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.ToDo
import com.survivalcoding.todolist.databinding.ToDoListItemLayoutBinding

class ToDoViewHolder private constructor(
    private val binding: ToDoListItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        toDo: ToDo,
        onItemCheckedChanged: (ToDo, Boolean) -> Unit,
        onDeleteButtonClick: (ToDo) -> Unit
    ) {
        bindTextView(toDo)
        bindCheckBox(toDo, onItemCheckedChanged)
        bindDeleteButton(toDo, onDeleteButtonClick)
    }

    private fun bindTextView(toDo: ToDo) {
        binding.titleTextView.text = toDo.title
        binding.titleTextView.setTextColor(getTextColor(toDo.isDone))
    }

    private fun getTextColor(isDone: Boolean) =
        if (isDone) TEXT_COLOR_DONE else TEXT_COLOR_DEFAULT

    private fun bindCheckBox(
        toDo: ToDo,
        onItemCheckedChanged: (ToDo, Boolean) -> Unit
    ) {
        binding.checkBox.setOnCheckedChangeListener(null)
        binding.checkBox.isChecked = toDo.isDone
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onItemCheckedChanged(toDo, isChecked)
        }
    }

    private fun bindDeleteButton(
        toDo: ToDo,
        onDeleteButtonClick: (ToDo) -> Unit
    ) {
        binding.deleteButton.setOnClickListener {
            onDeleteButtonClick(toDo)
        }
    }


    companion object {
        fun from(parent: ViewGroup): ToDoViewHolder {
            return ToDoViewHolder(
                ToDoListItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        private const val TEXT_COLOR_DONE = Color.LTGRAY
        private const val TEXT_COLOR_DEFAULT = Color.BLACK
    }
}
package com.survivalcoding.todolist.presentation.main.todolist.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.databinding.ToDoListItemLayoutBinding

class ToDoViewHolder private constructor(
    private val binding: ToDoListItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val doneTextColor: Int by lazy {
        val typedValue = TypedValue()
        itemView.context.theme.resolveAttribute(android.R.attr.textColorSecondary, typedValue, true)
        typedValue.data
    }
    private val defaultTextColor: Int by lazy {
        val typedValue = TypedValue()
        itemView.context.theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)
        typedValue.data
    }

    fun bind(
        toDo: ToDo,
        onItemCheckedChanged: (ToDo, Boolean) -> Unit,
        onDeleteButtonClick: (ToDo) -> Unit,
        onItemClick: (ToDo) -> Unit
    ) {
        itemView.setOnClickListener { onItemClick(toDo) }
        bindTextView(toDo)
        bindCheckBox(toDo, onItemCheckedChanged)
        bindDeleteButton(toDo, onDeleteButtonClick)
    }

    private fun bindTextView(toDo: ToDo) {
        binding.titleTextView.text = toDo.title
        binding.titleTextView.setTextColor(getTextColor(toDo.isDone))
    }

    private fun getTextColor(isDone: Boolean) =
        if (isDone) doneTextColor else defaultTextColor

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
    }
}
package com.survivalcoding.todolist.presentation.main.todolist.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
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
        onDeleteClick: (ToDo) -> Unit,
        onModifyClick: (ToDo) -> Unit
    ) {
        setContextMenu(toDo, onModifyClick, onDeleteClick)
        itemView.setOnClickListener { binding.checkBox.performClick() }
        bindTextView(toDo)
        bindCheckBox(toDo, onItemCheckedChanged)
    }

    private fun setContextMenu(
        toDo: ToDo,
        onModifyButtonClick: (ToDo) -> Unit,
        onDeleteButtonClick: (ToDo) -> Unit
    ) {
        itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->
            MenuInflater(itemView.context).inflate(
                R.menu.to_do_list_item_menu,
                menu
            )
            menu.forEach {
                when (it.itemId) {
                    R.id.menu_modify -> it.setOnMenuItemClickListener {
                        onModifyButtonClick(toDo)
                        true
                    }
                    R.id.menu_delete -> it.setOnMenuItemClickListener {
                        onDeleteButtonClick(toDo)
                        true
                    }
                }
            }
        }
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
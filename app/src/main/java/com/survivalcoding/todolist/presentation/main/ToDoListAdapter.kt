package com.survivalcoding.todolist.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.domain.model.ToDo

class ToDoListAdapter(
    private val onItemCheckedChanged: (ToDo, Boolean) -> Unit,
    private val onDeleteButtonClick: (ToDo) -> Unit,
    private val onItemClick: (ToDo) -> Unit
) : ListAdapter<ToDo, RecyclerView.ViewHolder>(ToDoDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == BODY_VIEW_TYPE)
            ToDoViewHolder.from(parent)
        else
            ToDoFooterViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ToDoViewHolder)
            holder.bind(getItem(position), onItemCheckedChanged, onDeleteButtonClick, onItemClick)
    }

    override fun getItemCount() = super.getItemCount() + 1

    override fun getItemViewType(position: Int) =
        if (position == currentList.size) FOOTER_VIEW_TYPE else BODY_VIEW_TYPE

    companion object {
        private const val BODY_VIEW_TYPE = 0
        private const val FOOTER_VIEW_TYPE = 1
    }
}

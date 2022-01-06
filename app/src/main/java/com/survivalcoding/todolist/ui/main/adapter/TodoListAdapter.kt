package com.survivalcoding.todolist.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.todolist.domain.entity.Todo

class TodoListAdapter(
    private val onItemClicked: (Todo) -> Unit,
    private val onLongClicked: (Todo) -> Unit,
    private val onLeftSwiped: (Todo) -> Unit
) :
    ListAdapter<Todo, TodoViewHolder>(TodoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder.builder(parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked, onLongClicked)
    }

    // diff.util의 비동기 처리를 고려해야한다고 본 것 같은데 잘되는 것 같다.
    fun removeItem(position: Int) {
        if (position >= itemCount) return
        onLeftSwiped(currentList.toMutableList()[position])
    }
}
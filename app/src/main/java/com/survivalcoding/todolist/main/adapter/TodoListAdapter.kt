package com.survivalcoding.todolist.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.model.Todo

class TodoListAdapter(private var items: List<Todo>) : RecyclerView.Adapter<TodoViewHolder>() {
    var onItemClicked: (Todo, Int) -> Unit = { _: Todo, _: Int -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitTodos(todos: List<Todo>, position: Int) {//todos를 교체한 후에 어댑터에 알려줌
        items = todos
        notifyItemChanged(position)
    }
}

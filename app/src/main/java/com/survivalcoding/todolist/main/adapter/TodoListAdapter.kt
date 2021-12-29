package com.survivalcoding.todolist.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo

class TodoListAdapter :
    RecyclerView.Adapter<TodoViewHolder>() {
    private var items: List<Todo> = listOf()
    private var itemClickListener: (Todo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickListener
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<Todo>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: (Todo) -> Unit) {
        this.itemClickListener = itemClickListener
    }
}

package com.survivalcoding.todolist

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ListItemTodoBinding

class TodoListAdapter(private var todos: List<Todo>, private val onItemClick: (Int) -> List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(private val binding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo, onClickListener: View.OnClickListener) {
            binding.todoListItemTv.text = todo.title
            binding.root.setBackgroundColor(if (todo.hasHighlight) Color.RED else Color.TRANSPARENT)
            binding.root.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ListItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position]) {
            todos = onItemClick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
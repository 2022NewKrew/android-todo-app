package com.survivalcoding.todolist

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.databinding.ListItemTodoBinding

class TodoListAdapter(private val todos: List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    class TodoViewHolder(private val binding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo, hasHighlight: Boolean, onClickListener: View.OnClickListener) {
            binding.todoListItemTv.text = todo.title
            binding.root.setBackgroundColor(if (hasHighlight) Color.RED else Color.TRANSPARENT)
            binding.root.setOnClickListener(onClickListener)
        }
    }

    companion object {
        private const val SAVED_INSTANCE_STATE_KEY = "saved_instance_state_key"
    }

    private var hasHighlights = todos.map { false }

    fun saveInstanceState(bundle: Bundle) {
        bundle.putBooleanArray(
            SAVED_INSTANCE_STATE_KEY,
            BooleanArray(hasHighlights.size) { hasHighlights[it] })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun restoreInstanceState(bundle: Bundle) {
        val restoredHighlights =
            bundle.getBooleanArray(SAVED_INSTANCE_STATE_KEY) ?: return

        if(hasHighlights.size == restoredHighlights.size) {
            hasHighlights = restoredHighlights.toList()
            notifyDataSetChanged()
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

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position], hasHighlights[position]) {
            hasHighlights = hasHighlights.mapIndexed { index, hasHighlight ->
                if(index == position) !hasHighlight else hasHighlight
            }
            it.setBackgroundColor(if (hasHighlights[position]) Color.RED else Color.TRANSPARENT)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
package com.survivalcoding.todolist.domain

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.databinding.ItemTodoBinding
import com.survivalcoding.todolist.model.Todo

class TodoListAdapter(private val items: MutableList<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    var onItemClicked: (Int) -> Unit = { _ -> }

    // view holder
    class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Todo,
            position: Int,
            onItemClicked: (Int) -> Unit,
        ) {
            binding.todoTextview.text = item.title
            if (item.isDone) binding.todoCardView.setBackgroundColor(Color.RED)
            else binding.todoCardView.setBackgroundColor(Color.TRANSPARENT)

            binding.todoTextview.setOnClickListener {
                onItemClicked(position)
            }
        }
    }

    //adapter setting

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return ViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, onItemClicked)
    }

    override fun getItemCount(): Int = items.size

    fun setItem(_item: Todo, position: Int) {
        items[position] = _item
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(_items: MutableList<Todo>) {
        items.clear()
        items.addAll(_items)
        notifyDataSetChanged()
    }
}
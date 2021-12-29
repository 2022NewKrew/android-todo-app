package com.survivalcoding.todolist.main.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.survivalcoding.todolist.model.Todo

class TodoListAdapter(private var items: List<Todo>) : BaseAdapter() {
    var onItemClicked: (Todo) -> Unit = {}
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Todo {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: TodoViewHolder = if (convertView == null) {
            TodoViewHolder(parent!!, onItemClicked)
        } else {
            convertView.tag as TodoViewHolder
        }
        holder.bind(getItem(position))

        return holder.binding.root
    }

    fun submitTodos(todos: List<Todo>) {//todos를 교체한 후에 어댑터에 알려줌
        items = todos
        notifyDataSetChanged()
    }
}

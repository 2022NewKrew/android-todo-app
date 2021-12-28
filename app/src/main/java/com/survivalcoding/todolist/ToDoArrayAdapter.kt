package com.survivalcoding.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ToDoArrayAdapter(private val todos: List<String>) : BaseAdapter() {

    class ViewHolder(view: View) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }

    private val isHighlighted = todos.map { false }.toMutableList()

    override fun getCount(): Int {
        return todos.size
    }

    override fun getItem(position: Int): Any {
        return todos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)

        view.setBackgroundColor(isHighlighted[position].toColor())

        view.setOnClickListener {
            isHighlighted[position] = !isHighlighted[position]
            view.setBackgroundColor(isHighlighted[position].toColor())
        }

        val viewHolder = (view.tag ?: ViewHolder(view)) as ViewHolder
        viewHolder.textView.text = todos[position]

        view.tag = viewHolder
        return view
    }

    private fun Boolean.toColor(): Int {
        return if (this) {
            Color.RED
        } else {
            Color.TRANSPARENT
        }
    }
}

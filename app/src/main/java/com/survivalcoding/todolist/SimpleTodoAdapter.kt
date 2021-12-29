package com.survivalcoding.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SimpleTodoAdapter(val items: List<String>) : BaseAdapter() {
    private val clickSet = mutableSetOf<Long>()

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: MyViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)

            holder = MyViewHolder()
            holder.textView = view.findViewById(android.R.id.text1)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as MyViewHolder
        }

        changeBackground(view, position)

        holder.textView.text = items[position]

        return view
    }

    fun onClick(view: View, position: Int) {
        clickSet.add(position.toLong())
        changeBackground(view, position)
    }

    private fun changeBackground(view: View, position: Int) {
        if (getItemId(position) in clickSet) {
            view.setBackgroundColor(Color.RED)
        } else {
            view.setBackgroundColor(Color.WHITE)
        }
    }
    class MyViewHolder {
        lateinit var textView: TextView
    }
}
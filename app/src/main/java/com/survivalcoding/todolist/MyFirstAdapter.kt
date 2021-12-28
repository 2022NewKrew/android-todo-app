package com.survivalcoding.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyFirstAdapter(val items: List<String>) : BaseAdapter() {
    val clickSet = mutableSetOf<String>()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): String {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder: MyViewHolder

        // Layout 설정
        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)

            holder = MyViewHolder()
            holder.numberTextView = view.findViewById(android.R.id.text1)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as MyViewHolder
        }

        // 아주 비쌈
        holder.numberTextView.text = items[position]

        return view
    }
}

class MyViewHolder {
    lateinit var numberTextView: TextView
}

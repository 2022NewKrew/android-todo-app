package com.survivalcoding.todolist.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TodoAdapter(private val items: List<String>) :
    BaseAdapter() {
    private val clickedPositions = mutableSetOf<Int>()

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = items[position].toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: TodoViewHolder

        // Layout 설정
        if (convertView == null) {
            // view 생성
            view = LayoutInflater.from(parent?.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)

            holder = TodoViewHolder()
            holder.tvNumber = view.findViewById(android.R.id.text1)

            view.tag = holder
        } else {
            // view 재사용
            view = convertView
            holder = view.tag as TodoViewHolder
        }

        holder.tvNumber.text = items[position]
        if (clickedPositions.contains(position)) view.setBackgroundColor(Color.LTGRAY)
        else view.setBackgroundColor(Color.WHITE)

        view.setOnClickListener {
            if (clickedPositions.contains(position)) {
                clickedPositions.remove(position)
                view.setBackgroundColor(Color.WHITE)
            } else {
                clickedPositions.add(position)
                view.setBackgroundColor(Color.LTGRAY)
            }
        }

        return view
    }

    class TodoViewHolder {
        lateinit var tvNumber: TextView
    }
}

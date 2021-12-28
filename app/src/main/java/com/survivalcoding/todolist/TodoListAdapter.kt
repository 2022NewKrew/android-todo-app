package com.survivalcoding.todolist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TodoListAdapter(private val items: List<SampleData>) : BaseAdapter() {

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
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
        // change color when click
        view.setOnClickListener {
            items[position].isClick = true
            view.setBackgroundColor(Color.RED)
        }

        // set text
        holder.numberTextView.text = items[position].title
        // color checking when view (re)used
        if (items[position].isClick) holder.numberTextView.setBackgroundColor(Color.RED)
        else holder.numberTextView.setBackgroundColor(Color.TRANSPARENT)
/*        else {
            if ((holder.numberTextView.background as ColorDrawable).color == Color.RED)
                holder.numberTextView.setBackgroundColor(Color.TRANSPARENT)
        }*/



        return view
    }
}

class MyViewHolder {
    lateinit var numberTextView: TextView
}


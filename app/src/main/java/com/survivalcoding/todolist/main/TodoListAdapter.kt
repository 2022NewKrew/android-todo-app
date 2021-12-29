package com.survivalcoding.todolist.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.model.Todo
import java.text.SimpleDateFormat
import java.util.*

class TodoListAdapter(private val items: MutableList<Todo>) : BaseAdapter() {
    private val clickSet = mutableSetOf<Int>() // click 정보 저장

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Todo {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return (position + 1).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: MyViewHolder

        val toDoItem = getItem(position)
        if (convertView == null) {
            view =
                LayoutInflater.from(parent!!.context).inflate(R.layout.item_todo, parent, false)
            holder = MyViewHolder()

            holder.titleText = view.findViewById(R.id.titleText)
            holder.timeText = view.findViewById(R.id.timeText)
            holder.contentText = view.findViewById(R.id.contentText)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as MyViewHolder
        }
        holder.titleText.text = toDoItem.title
        holder.timeText.text = SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(toDoItem.date)
        holder.contentText.text = toDoItem.content
        if (position in clickSet) view.setBackgroundColor(Color.RED) else view.setBackgroundColor(
            Color.TRANSPARENT
        )

        return view
    }

    fun setClicked(position: Int) {//click 여부를 clickSet에 저장
        if (position in clickSet) clickSet.remove(position)
        else clickSet.add(position)
    }
}

class MyViewHolder {
    lateinit var titleText: TextView
    lateinit var timeText: TextView
    lateinit var contentText: TextView
}

package com.survivalcoding.todolist

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ToDoListAdapter(val toDoList: List<Task>, val set: MutableSet<Int>) : BaseAdapter() {
    override fun getCount(): Int {
        return toDoList.size
    }

    override fun getItem(position: Int): Task {
        return toDoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ToDoListHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item, parent, false)
            holder = ToDoListHolder()
            holder.nameTextView = view.findViewById(R.id.taskNameTextView)
            holder.dateTextView = view.findViewById(R.id.taskDateTextView)
            holder.background = view.findViewById(R.id.item_background)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ToDoListHolder
        }

        val now = toDoList[position]
        val currentDate = LocalDate.now()
        holder.nameTextView.text = now.taskName
        holder.dateTextView.text =
            if (now.date == currentDate.format(DateTimeFormatter.ISO_DATE)) "today"
            else now.date

        // 클릭했을 때 한가지 아이템만 배경을 변경하는 방법을 못찾음.
//        if (now.isDone) {
//            holder.background.setBackgroundResource(R.drawable.shape_selected_to_do_item)
//        }

        return view
    }
}

class ToDoListHolder {
    lateinit var nameTextView: TextView
    lateinit var dateTextView: TextView
    lateinit var background: ConstraintLayout
}
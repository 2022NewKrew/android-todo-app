package com.survivalcoding.todolist.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.todolist.R
import com.survivalcoding.todolist.model.Task

class ToDoListAdapter(
    private var tasks: List<Task>,
) : RecyclerView.Adapter<ToDoListHolder>() {
    var clickEvent: (position: Int, tasks: Task) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ToDoListHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListHolder, position: Int) {
        holder.binding(position, tasks[position], clickEvent)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun submitItem(newTasks: List<Task>, position: Int? = null) {
//        val diffCallBack = DiffUtilCallBack(tasks, newTasks)
//        val diffResult = DiffUtil.calculateDiff(diffCallBack)
//
//        tasks = newTasks.apply { diffResult.dispatchUpdatesTo(this@ToDoListAdapter) }
        tasks = newTasks
        position?.let {
            notifyItemChanged(position)
        } ?: notifyItemRangeChanged(0, itemCount)
    }
}


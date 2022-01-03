package com.survivalcoding.todolist.ui.add

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.databinding.ActivityAddListBinding
import com.survivalcoding.todolist.domain.entity.Task
import java.text.SimpleDateFormat
import java.util.*

class AddViewModel : ViewModel() {
    fun makeTask(binding: ActivityAddListBinding): Task {
        val taskName = binding.taskNameInput.text.toString().trim()
        return Task(
            id = Date().time,
            taskName = if (taskName.isEmpty()) "empty task name" else taskName,
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
            isDone = false
        )
    }
}
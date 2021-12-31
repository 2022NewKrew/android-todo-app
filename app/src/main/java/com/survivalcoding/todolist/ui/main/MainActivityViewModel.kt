package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.data.repository.TodoRepository

class MainActivityViewModel : ViewModel() {
    val data = TodoRepository.data

    fun toggleTodoDone(id: Long) {
        val prevData = TodoRepository.findDataById(id)
        val newData =
            prevData.copy(isDone = !prevData.isDone)
        TodoRepository.updateDataByIndex(data.indexOf(prevData), newData)
    }

    fun setTodos(todos: List<TodoItem>) {
        TodoRepository.removeAllData()
        TodoRepository.addAllData(todos)
    }
}
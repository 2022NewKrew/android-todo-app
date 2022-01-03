package com.survivalcoding.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.model.TodoItem
import com.survivalcoding.todolist.data.repository.TodoRepository

class MainActivityViewModel : ViewModel() {
    private val _data = MutableLiveData(TodoRepository.getTodos())
    val data: LiveData<List<TodoItem>> = _data

    fun toggleTodoDone(id: Long) {
        val prevData = TodoRepository.findDataById(id)
        val newData =
            prevData.copy(isDone = !prevData.isDone)
        TodoRepository.updateDataByIndex(TodoRepository.getTodos().indexOf(prevData), newData)
    }

    fun setTodos(todos: List<TodoItem>) {
        TodoRepository.removeAllData()
        TodoRepository.addAllData(todos)
    }

    fun addTodo(todoItem: TodoItem) {
        TodoRepository.addData(todoItem)
    }
}
package com.survivalcoding.todolist.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.data.repository.TodoRepositoryImp

class AddViewModel : ViewModel() {
    private val repository = TodoRepositoryImp()
    private val _todoList = MutableLiveData(repository.todoList)
    val todoList get() = _todoList
}
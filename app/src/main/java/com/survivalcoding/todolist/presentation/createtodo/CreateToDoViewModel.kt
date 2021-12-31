package com.survivalcoding.todolist.presentation.createtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.ToDo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class CreateToDoViewModel: ViewModel() {

    private val _newToDoCreatedEvent = MutableSharedFlow<ToDo>()
    val newToDoCreatedEvent = _newToDoCreatedEvent.asSharedFlow()

    fun createNewToDo(toDoText: String) {
        viewModelScope.launch {
            _newToDoCreatedEvent.emit(ToDo(Date().time, toDoText))
        }
    }
}
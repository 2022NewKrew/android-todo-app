package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.ToDo
import com.survivalcoding.todolist.data.datasource.ToDoMockDataSource
import com.survivalcoding.todolist.data.repository.ToDoRepositoryImpl
import com.survivalcoding.todolist.domain.ToDoRepository

class MainViewModel : ViewModel() {
    private val toDoRepository: ToDoRepository = ToDoRepositoryImpl(ToDoMockDataSource())
    val toDoList = toDoRepository.toDoList

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        toDoRepository.updateItem(toDoList.value.indexOf(toDo), toDo.copy(isDone = isDone))
    }
}
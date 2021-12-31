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
        toDoRepository.updateItem(toDo.id, toDo.copy(isDone = isDone))
    }

    fun deleteToDo(toDo: ToDo) {
        toDoRepository.deleteItem(toDo.id)
    }

    fun addToDo(toDo: ToDo) {
        toDoRepository.addItem(toDo)
    }
}
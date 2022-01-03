package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.data.datasource.ToDoMockDataSource
import com.survivalcoding.todolist.data.repository.ToDoRepositoryImpl
import com.survivalcoding.todolist.domain.repository.ToDoRepository

class MainViewModel : ViewModel() {
    private val toDoRepository: ToDoRepository = ToDoRepositoryImpl(ToDoMockDataSource())
    val toDoList = toDoRepository.toDoList

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        toDoRepository.updateItem(toDo.id, toDo.copy(isDone = isDone))
    }

    fun updateToDo(id: Long, toDo: ToDo) {
        toDoRepository.updateItem(id, toDo.copy(id = id))
    }

    fun deleteToDo(toDo: ToDo) {
        toDoRepository.deleteItem(toDo.id)
    }

    fun addToDo(toDo: ToDo) {
        toDoRepository.addItem(toDo)
    }
}
package com.survivalcoding.todolist.presentation.main

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {
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
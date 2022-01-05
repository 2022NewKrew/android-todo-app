package com.survivalcoding.todolist.presentation.main.todolist

import androidx.lifecycle.ViewModel
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.DeleteToDoUseCase
import com.survivalcoding.todolist.domain.usecase.GetAllToDoUseCase
import com.survivalcoding.todolist.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val getAllToDoUseCase: GetAllToDoUseCase
) : ViewModel() {
    val toDoList = getAllToDoUseCase()

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        updateToDoUseCase(toDo.id, toDo.copy(isDone = isDone))
    }

    fun deleteToDo(toDo: ToDo) {
        deleteToDoUseCase(toDo.id)
    }
}
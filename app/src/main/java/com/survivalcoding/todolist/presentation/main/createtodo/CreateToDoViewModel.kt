package com.survivalcoding.todolist.presentation.main.createtodo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.AddToDoUseCase
import com.survivalcoding.todolist.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateToDoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val addToDoUseCase: AddToDoUseCase
) : ViewModel() {

    val prevToDo = savedStateHandle.get<ToDo>(TODO)

    fun createNewToDo(toDoText: String) {
        viewModelScope.launch {
            if (prevToDo != null) {
                updateToDoUseCase(prevToDo.id, prevToDo.copy(title = toDoText))
            } else {
                addToDoUseCase(ToDo(title = toDoText))
            }
        }
    }

    companion object {
        const val TODO = "TODO"
    }
}
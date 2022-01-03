package com.survivalcoding.todolist.presentation.createtodo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateToDoViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _newToDoCreatedEvent = MutableSharedFlow<CreateToDoEvent>()
    val newToDoCreatedEvent = _newToDoCreatedEvent.asSharedFlow()

    val prevToDo = savedStateHandle.get<ToDo>(TODO)

    fun createNewToDo(toDoText: String) {
        val createToDoEvent = if (prevToDo != null) {
            CreateToDoEvent(
                editedFlag = true,
                toDo = prevToDo.copy(title = toDoText)
            )
        } else {
            CreateToDoEvent(
                editedFlag = false,
                toDo = ToDo(id = Date().time, title = toDoText)
            )
        }

        viewModelScope.launch {
            _newToDoCreatedEvent.emit(createToDoEvent)
        }
    }

    companion object {
        const val TODO = "TODO"
    }
}
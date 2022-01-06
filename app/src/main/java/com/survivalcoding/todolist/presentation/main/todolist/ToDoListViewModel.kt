package com.survivalcoding.todolist.presentation.main.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.DeleteToDoUseCase
import com.survivalcoding.todolist.domain.usecase.GetMatchingToDosUseCase
import com.survivalcoding.todolist.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val getMatchingToDosUseCase: GetMatchingToDosUseCase
) : ViewModel() {

    private var toDoListOrder = OrderBy.TIME_ASC
        set(value) {
            field = value
            _toDoList.value = getMatchingToDosUseCase(currentQuery, value)
        }

    private var searchJob: Job? = null
    private var currentQuery: String = ""

    private val _toDoList = MutableStateFlow(getMatchingToDosUseCase(currentQuery, toDoListOrder))
    val toDoList = _toDoList.asStateFlow()

    fun setOrder(orderBy: OrderBy) {
        toDoListOrder = orderBy
    }

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        updateToDoUseCase(toDo.id, toDo.copy(isDone = isDone))
    }

    fun deleteToDo(toDo: ToDo) {
        deleteToDoUseCase(toDo.id)
    }

    fun searchToDo(query: CharSequence?) {
        if (query == null) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME)
            if (isActive) {
                currentQuery = query.toString()
                _toDoList.value = getMatchingToDosUseCase(currentQuery, toDoListOrder)
            }
        }
    }


    companion object {
        private const val DEBOUNCE_TIME = 300L
    }
}
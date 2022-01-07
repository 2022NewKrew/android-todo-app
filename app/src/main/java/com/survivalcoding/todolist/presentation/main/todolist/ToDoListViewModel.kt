package com.survivalcoding.todolist.presentation.main.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.todolist.domain.OrderBy
import com.survivalcoding.todolist.domain.model.ToDo
import com.survivalcoding.todolist.domain.usecase.*
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
    private val sortToDoListUseCase: SortToDoListUseCase,
    private val getMatchingToDosUseCase: GetMatchingToDosUseCase
) : ViewModel() {

    private var searchJob: Job? = null
    private var currentQuery: String = ""

    private val _toDoListUiState = MutableStateFlow(ToDoListUiState(listOf(), OrderBy.TIME_ASC))
    val toDoListUiState = _toDoListUiState.asStateFlow()

    fun setOrder(orderBy: OrderBy) {
        _toDoListUiState.value = _toDoListUiState.value.copy(
            orderBy = orderBy
        )
    }

    fun changeDoneState(toDo: ToDo, isDone: Boolean) {
        viewModelScope.launch {
            updateToDoUseCase(toDo.id, toDo.copy(isDone = isDone))

            _toDoListUiState.value = _toDoListUiState.value.copy(
                toDoList = getMatchingToDosUseCase(currentQuery)
            )
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            deleteToDoUseCase(toDo.id)

            _toDoListUiState.value = _toDoListUiState.value.copy(
                toDoList = getMatchingToDosUseCase(currentQuery)
            )
        }
    }

    fun searchToDo(query: CharSequence?) {
        if (query == currentQuery) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME)
            if (isActive) {
                currentQuery = query.toString()
                getMatchingToDos(currentQuery)
            }
        }
    }

    fun sortToDoList(toDoList: List<ToDo>, orderBy: OrderBy) = sortToDoListUseCase(toDoList, orderBy)

    private fun getMatchingToDos(query: String) {
        viewModelScope.launch {
            _toDoListUiState.value = _toDoListUiState.value.copy(
                toDoList = getMatchingToDosUseCase(query)
            )
        }
    }


    companion object {
        private const val DEBOUNCE_TIME = 300L
    }
}

data class ToDoListUiState(
    val toDoList: List<ToDo>,
    val orderBy: OrderBy
)